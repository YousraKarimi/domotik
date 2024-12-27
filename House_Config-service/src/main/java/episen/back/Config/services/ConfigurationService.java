package episen.back.Config.services;


import episen.back.Config.models.Configuration;
import episen.back.Config.models.Device;
import episen.back.Config.repositories.ConfigurationRepository;
import episen.back.Config.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public Configuration addConfiguration(Long deviceId, Configuration configuration) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));
        if (device.getConfiguration() != null) {
            throw new RuntimeException("Device already has a configuration. Update the existing one.");
        }
        configuration.setDevice(device);
        Configuration savedConfig = configurationRepository.save(configuration);
        device.setConfiguration(savedConfig);
        deviceRepository.save(device);

        return savedConfig;
    }

    public Configuration updateConfiguration(Long deviceId, Configuration configDetails) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));

        Configuration existingConfig = configurationRepository.findByDevice(device)
                .orElseThrow(() -> new RuntimeException("No existing configuration to update."));

        existingConfig.setConfigurationName(configDetails.getConfigurationName());
        existingConfig.setMode(configDetails.getMode());
        existingConfig.setSchedule(configDetails.getSchedule());
        existingConfig.setEnergySaving(configDetails.getEnergySaving());

        return configurationRepository.save(existingConfig);
    }

//    public Configuration updateConfiguration(Long id, Configuration configDetails) {
//        Configuration existingConfig = configurationRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Configuration not found with id: " + id));
//        existingConfig.setConfigurationName(configDetails.getConfigurationName());
//        existingConfig.setMode(configDetails.getMode());
//        existingConfig.setSchedule(configDetails.getSchedule());
//        existingConfig.setEnergySaving(configDetails.getEnergySaving());
//
//        return configurationRepository.save(existingConfig);
//    }

    public List<Configuration> getAllConfigurations() {
        return configurationRepository.findAll();
    }
}
