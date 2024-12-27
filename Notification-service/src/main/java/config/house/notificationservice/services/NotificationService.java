package config.house.notificationservice.services;
import config.house.notificationservice.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private static final String KEY_PREFIX = "notifications:user:";

    @Autowired
    private RedisTemplate<String, Notification> redisTemplate;


    public void saveNotification(String userId, Notification notification) {
        redisTemplate.opsForList().rightPush(KEY_PREFIX + userId, notification);
    }

    public List<Notification> getNotificationsForUser(String userId) {
        String key = KEY_PREFIX + userId;
        List<Notification> notifications = redisTemplate.opsForList().range(key, 0, -1);
        return notifications;
    }


    }