package config.house.notificationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import config.house.notificationservice.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add/{userId}")
    public void createNotification(@PathVariable String userId, @RequestBody String notification) {
        notificationService.saveNotification(userId, notification);
    }

    @GetMapping("/all/{userId}")
    public List<String> getUserNotifications(@PathVariable String userId) {
        return notificationService.getAndDeleteNotificationsForUser(userId);
    }

    @GetMapping("/redis/list/{key}")
    public List<String> getListFromRedis(@PathVariable String key) {
        return notificationService.getListFromRedis(key);
    }

    @GetMapping("/redis/value/{key}")
    public String getValueFromRedis(@PathVariable String key) {
        return notificationService.getValueFromRedis(key);
    }
}

