package raf.service.impl;

import com.User.exception.NotFoundException;
import com.User.repository.ClientRepository;
import io.github.resilience4j.retry.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raf.domain.Rezervacija;
import raf.domain.Trening;
import raf.dto.*;
import raf.listener.helper.MessageHelper;
import raf.mapper.RezervacijaMapper;
import raf.mapper.TreningMapper;
import raf.repository.RezervacijaRepository;
import raf.repository.TreningRepository;
import raf.service.RezervacijaService;
import raf.service.TreningService;
import raf.userservice.ClientDto;

import java.util.List;

@Service
@AllArgsConstructor
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    private TreningRepository treningRepository;

    private JmsTemplate jmsTemplate;

    private MessageHelper messageHelper;

    private RestTemplate userServiceRestTemplate;

    private Retry userServiceRetry;

    @Value("${destination.incrementReservation}")
    private String destination;
    @Value("${destination.cancelledReservation}")
    private String destination2;
    @Value("${destination.cancelledTraining}")
    private String destination3;

    @Override
    public Page<RezervacijaDto> findAll(Pageable pageable) {
        return rezervacijaRepository.findAll(pageable).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override
    public Page<RezervacijaDto> findByClientId(Pageable pageable,Long client_id) {

        return rezervacijaRepository.findAllByClientID(pageable,client_id).map(rezervacijaMapper::DomainObjectToDto);
    }

    @Override
    public RezervacijaDto add(RezervacijaCreateDto rezervacijaCreateDto)
    {
        Trening trening = treningRepository.findById(rezervacijaCreateDto.getRezervisaniTrening().getTrening_id()).orElseThrow(RuntimeException::new);
        rezervacijaCreateDto.setRezervisaniTrening(trening);
        Rezervacija rezervacija = rezervacijaMapper.DtoToDomainObject(rezervacijaCreateDto);
        for(Rezervacija r: rezervacijaRepository.findAll()){
            if(r.getClientID().equals(rezervacija.getClientID()) && r.getRezervisaniTrening().equals(rezervacija.getRezervisaniTrening()) &&
                    (r.getRezervisaniTrening().getTerminTreninga().equals(rezervacija.getRezervisaniTrening().getTerminTreninga())
                    && r.getRezervisaniTrening().getPocetakTermina().equals(rezervacija.getRezervisaniTrening().getPocetakTermina()))
                    &&  r.getRezervisaniTrening().getKrajTermina().equals(rezervacija.getRezervisaniTrening().getKrajTermina())){
                return null;
            }
        }

        ClientDto clientDto = null;

        clientDto = Retry.decorateSupplier(userServiceRetry,()->getClient(rezervacijaCreateDto.getClientID())).get();

        if(trening.getSala().getKapacitet() == trening.getBrRezervacija())
            return null;
        if((clientDto.getBrojZakazanihTreninga()+1) % trening.getSala().getLoyalty() == 0)
            rezervacijaCreateDto.setCenaTreninga(0);
        else
            rezervacijaCreateDto.setCenaTreninga(trening.getCenaTreninga());

        rezervacijaCreateDto.setRezervisaniTrening(treningRepository.findById(trening.getTrening_id()).orElseThrow((RuntimeException::new)));
        rezervacijaCreateDto.setClientID(clientDto.getId());
        trening.setBrRezervacija(trening.getBrRezervacija()+1);
        rezervacija.setCenaTreninga(rezervacijaCreateDto.getCenaTreninga());

        rezervacijaRepository.save(rezervacija);
        RezervacijaDto rDto = rezervacijaMapper.DomainObjectToDto(rezervacija);

        IncrementReservationDto incrementReservationDto = new IncrementReservationDto();
        incrementReservationDto.setClient_id(rezervacijaCreateDto.getClientID());
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(incrementReservationDto));

        return rDto;
//            Rezervacija rezervacija = rezervacijaMapper.DtoToDomainObject(rezervacijaCreateDto);
//            rezervacijaRepository.save(rezervacija);
//            return rezervacijaMapper.DomainObjectToDto(rezervacija);
    }
    private ClientDto getClient(Long id){
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/" + id + "/getClient",
                    HttpMethod.GET,null,ClientDto.class);

            return clientDtoResponseEntity.getBody();
        }
        catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with this id %d has not been found! ",id));
        }
        catch(Exception e){
            throw new RuntimeException("Internal server error");
        }
        return null;
    }
    @Override
    public RezervacijaDto update(RezervacijaUpdateDto rezervacijaUpdateDto) {
        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaUpdateDto.getId()).orElseThrow(RuntimeException::new);

        return rezervacijaMapper.DomainObjectToDto(rezervacija);
    }

    @Override
    public void deleteByRezervisaniTrening(Trening trening) {

        List<Rezervacija> rezervacije =rezervacijaRepository.findAllByRezervisaniTrening(trening);

        for (Rezervacija rezervacija : rezervacije) {
            rezervacijaRepository.deleteById(rezervacija.getRezervacija_id());

            ClientDto clientDto = null;

            clientDto = Retry.decorateSupplier(userServiceRetry, () -> getClient(rezervacija.getClientID())).get();

            jmsTemplate.convertAndSend(destination3,messageHelper.createTextMessage(clientDto));

        }


    }

    @Override
    public void deleteById(Long id) { // od strane menadzera il admina

        Rezervacija rezervacija = rezervacijaRepository.findById(id).orElseThrow(RuntimeException::new);

        Trening trening = rezervacija.getRezervisaniTrening();
        if(trening.getBrRezervacija()-1<0){
            trening.setBrRezervacija(0);
        }
        else
        trening.setBrRezervacija(trening.getBrRezervacija()-1);

        treningRepository.save(trening);
        jmsTemplate.convertAndSend(destination2,messageHelper.createTextMessage(id));

        rezervacijaRepository.deleteById(id);
    }

    @Override
    public void deleteByIdClient(Long id, Long resid) { // od strane menadzera ili klijenta ili admina

        Rezervacija rezervacija = rezervacijaRepository.findById(resid).orElseThrow(RuntimeException::new);

        Trening trening = rezervacija.getRezervisaniTrening();
        if(trening.getBrRezervacija()-1<0){
            trening.setBrRezervacija(0);
        }
        else
            trening.setBrRezervacija(trening.getBrRezervacija()-1);

        treningRepository.save(trening);

        jmsTemplate.convertAndSend(destination2,messageHelper.createTextMessage(id));

        rezervacijaRepository.deleteById(resid);
    }
}
