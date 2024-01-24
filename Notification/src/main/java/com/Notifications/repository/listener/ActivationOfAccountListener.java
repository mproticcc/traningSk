package com.Notifications.repository.listener;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.ClientDto;
import com.Notifications.repository.listener.helper.MessageHelper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import com.Notifications.service.EmailService;
import com.Notifications.service.NotifikacijaService;
import jakarta.jms.JMSException;
import lombok.AllArgsConstructor;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@AllArgsConstructor
@Component
public class ActivationOfAccountListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotifikacijaService notifikacijaService;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;
    @JmsListener(destination = "${destination.activateAccount}", concurrency = "5-10")
    public void sendActivationMail(Message message) throws JMSException {
        ClientDto clientDto = messageHelper.getMessage(message, ClientDto.class);
        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setTipNotifikacije(notificationTypeRepository.findById(6L).orElseThrow(RuntimeException::new));
        notifikacija.setDatumSlanja(LocalDateTime.now());
        notifikacija.setText("You have successfully activated your account, please log in!");
        notifikacija.setLink("Link");
        notifikacija.setClientID(clientDto.getId());
        notificationRepository.save(notifikacija);

        emailService.sendSimpleMessage(clientDto.getEmail(), "Successful Activation", "You have successfully activated your account, please log in!");
    }



}
