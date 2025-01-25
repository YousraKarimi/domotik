package config.house.notificationservice.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceExceptionConverter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    private static final String KEY_PREFIX = "notifications:user:";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveNotification(String userId, String message) {
        redisTemplate.opsForList().rightPush(userId, message);
    }

    public List<String> getNotificationsForUser(String userId) {
        try {
            log.info("Retriving errors for : {}", userId);
            List<String> messages = redisTemplate.opsForList().range(userId, 0, -1);
            return (messages != null) ? messages : Collections.emptyList();
        }catch (Exception e) {
            log.error("Error retrieving notifications for user: {}", userId);
            log.error(Arrays.toString(e.getStackTrace()));
            return Collections.emptyList();
        }
    }

    public List<String> getListFromRedis(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);  // Récupère toute la liste
    }

    // Lire une valeur d'une clé simple
    public String getValueFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}