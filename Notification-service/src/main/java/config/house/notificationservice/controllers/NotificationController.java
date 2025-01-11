package config.house.notificationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import config.house.notificationservice.models.Notification;
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
        return notificationService.getNotificationsForUser(userId);
    }

}

