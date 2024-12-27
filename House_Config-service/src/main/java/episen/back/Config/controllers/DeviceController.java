package episen.back.Config.controllers;

import episen.back.Config.models.Configuration;
import episen.back.Config.models.Device;
import episen.back.Config.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {



    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/{deviceId}/assignConfig/{configurationId}")
    public ResponseEntity<String> assignConfigurationToDevice(
            @PathVariable Long deviceId,
            @PathVariable Long configurationId) {
        deviceService.assignConfigurationToDevice(deviceId, configurationId);
        return ResponseEntity.ok("Configuration assigned to device successfully.");
    }

    @PostMapping("/{deviceId}/assignUser/{userId}")
    public ResponseEntity<String> assignDeviceToUser(
            @PathVariable Long deviceId,
            @PathVariable Long userId) {
        deviceService.assignDeviceToUser(deviceId, userId);
        return ResponseEntity.ok("Device assigned to user successfully.");
    }
    @GetMapping("/myDevices/{fullName}")
    public ResponseEntity<List<Device>> getDevicesByFullName(@PathVariable String fullName) {
        List<Device> devices = deviceService.getDevicesByUserFullName(fullName);

        if (devices.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(devices);
        }
    }

    @GetMapping("/configurations/{fullName}")
    public ResponseEntity<?> getConfigurationsByFullName(@PathVariable String fullName) {
        try {
            List<Configuration> configurations = deviceService.getConfigurationsByFullName(fullName);
            if (configurations.isEmpty()) {
                return ResponseEntity.status(404).body("No configurations found for user: " + fullName);
            }
            return ResponseEntity.ok(configurations);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
