package episen.back.Config.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.back.Config.models.Configuration;
import episen.back.Config.producer.QueueProducer;
import episen.back.Config.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private QueueProducer queueProducer;


    @PostMapping("/add/{deviceId}")
    public ResponseEntity<?> addConfiguration(@PathVariable Long deviceId, @RequestBody Configuration configuration) {
        try {
            Configuration savedConfig = configurationService.addConfiguration(deviceId, configuration);
            Long configId = savedConfig.getId();
            String stringedId = String.valueOf(Math.toIntExact(configId));
            queueProducer.sendMessage(stringedId);
            return ResponseEntity.ok(savedConfig);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @PutMapping("/update/{deviceId}")
    public ResponseEntity<?> updateConfiguration(@PathVariable Long deviceId, @RequestBody Configuration configuration) {
        try {
            Configuration updatedConfig = configurationService.updateConfiguration(deviceId, configuration);
            Long configId = updatedConfig.getId();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonConfigId = objectMapper.writeValueAsString(configId);
            queueProducer.sendMessage(jsonConfigId);
            return ResponseEntity.ok(updatedConfig);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Configuration>> getAllConfigurations() {
        List<Configuration> configurations = configurationService.getAllConfigurations();
        return ResponseEntity.ok(configurations);
    }


}

