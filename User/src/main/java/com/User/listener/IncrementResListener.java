package com.User.listener;

import com.User.dto.IncrementReservationDto;
import com.User.listener.helper.MessageHelper;
import com.User.service.ClientService;
import jakarta.jms.JMSException;
import lombok.AllArgsConstructor;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class IncrementResListener {

    private MessageHelper messageHelper;

    private ClientService clientService;

    @JmsListener(destination = "${destination.incrementReservation}")
    public void incrementReservation(Message message) throws JMSException {
        IncrementReservationDto incrementReservationDto = messageHelper.getMessage(message,IncrementReservationDto.class);
        clientService.addReservation(incrementReservationDto.getClient_id());
    }

}
