package config.house.notificationservice.services;
import config.house.notificationservice.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private static final String KEY_PREFIX = "notifications:user:";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveNotification(String userId, String message) {
        redisTemplate.opsForList().rightPush(KEY_PREFIX + userId, message);
    }

    public List<String> getNotificationsForUser(String userId) {
        String key = KEY_PREFIX + userId;
        List<String> messages = redisTemplate.opsForList().range(key, 0, -1);
        return messages != null ? messages : List.of();
    }


    }