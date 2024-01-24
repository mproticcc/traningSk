package com.Notifications.service.impl;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.NotifikacijaDto;
import com.Notifications.dto.NotifikacijeCreateDto;
import com.Notifications.mapper.NotifikacijaMapper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.service.EmailService;
import com.Notifications.service.NotifikacijaService;
import com.Notifications.userservice.ClientDto;
import com.User.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
@EnableAsync
@Service
@AllArgsConstructor
public class NotifikacijaServiceImpl implements NotifikacijaService {

    private NotificationRepository notificationRepository;
    private NotifikacijaMapper notifikacijaMapper;


    private RestTemplate userServiceRestTemplate;
    private EmailService emailService;

    @Override
    public NotifikacijaDto add(NotifikacijeCreateDto notifikacijeCreateDto) {
        Notifikacija notifikacija = notifikacijaMapper.notifikacijaCreateDto(notifikacijeCreateDto);
        notificationRepository.save(notifikacija);
        return notifikacijaMapper.notifikacijaToDto(notifikacija);
    }

    @Override
    public Page<NotifikacijaDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable).map(notifikacijaMapper::notifikacijaToDto);
    }

    @Override
    public void posaljiAktivacioniImejl(Notifikacija notifikacija,Long id) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Aktivacioni emial
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        ClientDto clientDto = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange(id + "/getClient", HttpMethod.GET,null, ClientDto.class);

            clientDto = clientDtoResponseEntity.getBody();

        } catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with id: %d not found.",id));
        }
        catch (Exception e){
            e.printStackTrace();
        }
       // String messageBody = String.format("Pozdrav, "+clientDto.getFirstName() + clientDto.getLastName() +" Za verifikaciju posetite sledeći link: %s", notifikacija.getLink());
    }
    @Async
    @Override
    public void posaljiImejlZaPromenuLozinke(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Promena lozinke
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/", HttpMethod.GET,null, ClientDto.class);
        } catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with id: %d not found. notifikacija.getClientId()"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String messageBody = String.format("Pozdrav, "+clientDtoResponseEntity.getBody().getFirstName() + clientDtoResponseEntity.getBody().getLastName() + " Za promenu lozinke posetite sledeći link: %s", notifikacija.getLink());

    }
    @Async
    @Override
    public void posaljiNotifikacijuZakazivanja(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Zakazivanje treninga
        String text = notifikacija.getText();//Trening je uspešno zakazan.

        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/", HttpMethod.GET,null, ClientDto.class);
        } catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with id: %d not found. notifikacija.getClientId()"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiNotifikacijuOtkazivanja(Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Otkazivanje treninga
        String text = notifikacija.getText();//Trening je otkazan.

        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try{
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/", HttpMethod.GET,null, ClientDto.class);
        } catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Client with id: %d not found. notifikacija.getClientId()"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        notificationRepository.save(notifikacija);
    }
    @Async
    @Override
    public void posaljiPodsetnik(Date datumTreninga,Notifikacija notifikacija) {
        String subject = String.valueOf(notifikacija.getTipNotifikacije());//Podsetnik za trening
        String text = notifikacija.getText();//Vaš trening počinje za 24 sata.

        long razlika = datumTreninga.getTime() - System.currentTimeMillis();
        long jedanDan = 24 * 60 * 60 * 1000; // Milisekunde u danu

        if (razlika <= jedanDan) {
            ResponseEntity<ClientDto> clientDtoResponseEntity = null;
            try{
                clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/", HttpMethod.GET,null, ClientDto.class);
            } catch (HttpClientErrorException e){
                if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                    throw new NotFoundException(String.format("Client with id: %d not found. notifikacija.getClient()"));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            notificationRepository.save(notifikacija);
        }
    }
}
