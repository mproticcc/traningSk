package raf.listener.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.*;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Set;


@Component
@AllArgsConstructor
public class MessageHelper {

    private ObjectMapper objectMapper;

    public <T> T getMessage(Message message, Class<T> clazz) throws RuntimeException, JMSException {
        try {
            String json = message.toString();
            //String json = ((ActiveMQTextMessage)message).getText();
            T data = objectMapper.readValue(json,clazz);

            return data;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String createTextMessage(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Problem with creating text message");
        }
    }

    private <T> void printViolationsAndThrowException(Set<ConstraintViolation<T>> violations) {

    }
}

