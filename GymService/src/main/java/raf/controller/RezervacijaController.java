package raf.controller;

import com.User.domain.Client;
import com.User.exception.NotFoundException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.github.resilience4j.retry.Retry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import raf.domain.Trening;
import raf.dto.RezervacijaCreateDto;
import raf.dto.RezervacijaDto;
import raf.dto.RezervacijaUpdateDto;
import raf.dto.TreningDto;
import raf.listener.helper.MessageHelper;
import raf.security.CheckSecurity;
import raf.service.RezervacijaService;
import raf.service.TreningService;
import raf.userservice.ClientDto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder.decode;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class RezervacijaController {

    private RezervacijaService rezervacijaService;

    @GetMapping("/getAllReservation")
    //@CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity<Page<RezervacijaDto>> getAllRezervacije( Pageable pageable){
        return new ResponseEntity<>(rezervacijaService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/client")
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity<Page<RezervacijaDto>> getAllRezervacijeClient(@RequestHeader("Authorization") String authorization, Pageable pageable){
        String[] parts = authorization.split(" ");
        String[] keyParts = parts[1].split("\\.");
        byte[] decodedPayload = Base64.getDecoder().decode(keyParts[1]);
        String decodedStr = new String(decodedPayload, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(decodedStr);
        Long clientId = jsonObject.getLong("id");
        return new ResponseEntity<>(rezervacijaService.findByClientId(pageable,clientId), HttpStatus.OK);
    }

    @PostMapping("/registerReservation")
    public ResponseEntity<RezervacijaDto> addReservation(@RequestBody @Valid RezervacijaCreateDto rezervacijaCreateDto){
//        if(rezervacijaService.add(rezervacijaCreateDto) == null){
//            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(rezervacijaService.add(rezervacijaCreateDto),HttpStatus.OK);
    }



    @PutMapping
    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity<RezervacijaDto> updateReservation(@RequestHeader("Authorization") String authorization,RezervacijaUpdateDto rezervacijaUpdateDto){
        return new ResponseEntity<>(rezervacijaService.update(rezervacijaUpdateDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reservationid}")
//    @CheckSecurity(roles={"ROLE_ADMIN","ROLE_MANAGER"})
    public ResponseEntity deleteReservation(@PathVariable("reservationid") Long id){ //id rezervacije koju dobijamo kada kliknemo na rezervaciju koju otkazujemo

        rezervacijaService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}/{resid}")
    //@CheckSecurity(roles={"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity<Void> deleteReservationClient(@PathVariable Long id, @PathVariable("resid") Long resid){ //clientId

        rezervacijaService.deleteByIdClient(id,resid);


        return new ResponseEntity<>(HttpStatus.OK);
    }


}
