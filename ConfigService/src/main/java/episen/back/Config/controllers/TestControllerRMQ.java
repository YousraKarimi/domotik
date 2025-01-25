package episen.back.Config.controllers;


import episen.back.Config.services.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/test")
    public class TestControllerRMQ {

        private final QueueService queueService;

        public TestControllerRMQ(QueueService queueService) {
            this.queueService = queueService;
        }

        @GetMapping("/rabbitmq")
        public ResponseEntity<String> testRabbitMQ() {
            try {
                queueService.sendToConfigQueue("topic1", "Test Message to RabbitMQ");
                return ResponseEntity.ok("Message envoyé à RabbitMQ avec succès !");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Erreur de connexion à RabbitMQ : " + e.getMessage());
            }
        }
    }

