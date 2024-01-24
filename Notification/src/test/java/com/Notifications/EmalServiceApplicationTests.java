package com.Notifications;

import com.Notifications.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmalServiceApplicationTests {

    @Autowired
    EmailService emailService;

    @Test
    void contextLoads() {
    }

    @Test
    void sendEmail() {

        emailService.sendSimpleMessage("nikolajr93og@gmail.com", "Subject", "Tekst imejla!");


    }

}
