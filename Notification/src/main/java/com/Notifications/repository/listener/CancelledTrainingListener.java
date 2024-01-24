package com.Notifications.repository.listener;

import com.Notifications.domain.Notifikacija;
import com.Notifications.dto.ClientDto;
import com.Notifications.repository.listener.helper.MessageHelper;
import com.Notifications.repository.NotificationRepository;
import com.Notifications.repository.NotificationTypeRepository;
import com.Notifications.service.EmailService;
import jakarta.jms.JMSException;
import lombok.AllArgsConstructor;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CancelledTrainingListener{

    private MessageHelper messageHelper;

    private EmailService emailService;

    private NotificationRepository notificationRepository;

    private NotificationTypeRepository notificationTypeRepository;

    @JmsListener(destination = "${destination.cancelledTraining}",concurrency = "5-10")
    private void cancelTraining(Message message) throws JMSException {

        ClientDto clientDto = messageHelper.getMessage(message, ClientDto.class);
        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setTipNotifikacije(notificationTypeRepository.findById(4L).orElseThrow(RuntimeException::new));
        notifikacija.setDatumSlanja(LocalDateTime.now());
        notifikacija.setText("We are sorry to inform you that your reservation for training");
        notifikacija.setLink("Link");
        notifikacija.setClientID(clientDto.getId());
        notificationRepository.save(notifikacija);

        emailService.sendSimpleMessage(clientDto.getEmail(),"Training cancellation","We are sorry to inform you that your reservation for training" +
                " has been cancelled.");

    }


}
