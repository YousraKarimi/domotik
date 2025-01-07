package episen.back.Config.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueProducer {

    private final RabbitTemplate rabbitTemplate;

    public QueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend("config_exchange", "config_routing_key", message);
        System.out.println("Message envoyé à RabbitMQ : " + message);
    }
}
