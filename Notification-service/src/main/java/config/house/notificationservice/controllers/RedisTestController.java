package config.house.notificationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-test")
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/ping")
    public String testRedisConnection() {
        try {
            redisTemplate.opsForValue().set("testKey", "Hello Redis!");
            String value = redisTemplate.opsForValue().get("testKey");
            return "Redis fonctionne ! Valeur : " + value;
        } catch (Exception e) {
            return "Erreur de connexion à Redis : " + e.getMessage();
        }
    }
}
