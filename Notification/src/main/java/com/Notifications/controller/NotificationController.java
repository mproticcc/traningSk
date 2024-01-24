package com.Notifications.controller;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.*;
import com.Notifications.security.CheckSecurity;
import com.Notifications.service.NotifikacijaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotifikacijaService notificationService;

    @PostMapping
    @CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<NotifikacijaDto> addNotification(@RequestBody NotifikacijeCreateDto NotifikacijeCreateDto) {
        return new ResponseEntity<>(notificationService.add(NotifikacijeCreateDto), HttpStatus.CREATED);

    }
    @GetMapping("/getAllNoti")
    //@CheckSecurity(roles={"ROLE_ADMIN"})
    public ResponseEntity<Page<NotifikacijaDto>> getAllNotification(Pageable pageable) {
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/{id}/send-activation-email")
    public ResponseEntity<String> sendActivationEmail(@RequestBody Notifikacija notifikacija,@PathVariable("id") Long id) {
        notificationService.posaljiAktivacioniImejl(notifikacija,id);
        return ResponseEntity.ok("Activation email sent successfully.");
    }

    @PostMapping("/send-password-change-email")
    public ResponseEntity<String> sendPasswordChangeEmail(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiImejlZaPromenuLozinke(notifikacija);
        return ResponseEntity.ok("Password change email sent successfully.");
    }

    @PostMapping("/send-schedule-notification")
    public ResponseEntity<String> sendScheduleNotification(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiNotifikacijuZakazivanja(notifikacija);
        return ResponseEntity.ok("Schedule notification sent successfully.");
    }

    @PostMapping("/send-cancellation-notification")
    public ResponseEntity<String> sendCancellationNotification(@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiNotifikacijuOtkazivanja(notifikacija);
        return ResponseEntity.ok("Cancellation notification sent successfully.");
    }

    @PostMapping("/send-reminder")
    public ResponseEntity<String> sendReminder(@RequestBody Date trainingDate,@RequestBody Notifikacija notifikacija) {
        notificationService.posaljiPodsetnik(trainingDate,notifikacija);
        return ResponseEntity.ok("Reminder sent successfully.");
    }
}

