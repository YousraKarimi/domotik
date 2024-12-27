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

    @PostMapping("/{userId}")
    public void createNotification(@PathVariable String userId, @RequestBody Notification notification) {
        notificationService.saveNotification(userId, notification);
    }

    @GetMapping("/{userId}")
    public List<Notification> getUserNotifications(@PathVariable String userId) {
        return notificationService.getNotificationsForUser(userId);
    }

}

