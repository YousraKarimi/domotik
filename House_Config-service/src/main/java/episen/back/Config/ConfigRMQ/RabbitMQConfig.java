package episen.back.Config.ConfigRMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange configExchange() {
        return new TopicExchange("config_exchange", true, false);
    }

    @Bean
    public Queue configQueue() {
        return new Queue("config_queue", true);
    }

    @Bean
    public Binding bindingConfig() {
        return BindingBuilder
                .bind(configQueue())
                .to(configExchange())
                .with("config_routing_key");
    }
}

