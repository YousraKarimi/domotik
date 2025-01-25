package episen.back.Config.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    private final RabbitTemplate rabbitTemplate;

    public QueueService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToConfigQueue(String routingKey, Object message) {
        rabbitTemplate.convertAndSend("config-exchange", routingKey, message);
    }
}
