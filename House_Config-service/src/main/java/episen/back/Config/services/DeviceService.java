package episen.back.Config.services;

import episen.back.Config.models.Configuration;
import episen.back.Config.models.Device;
import episen.back.Config.models.User;
import episen.back.Config.repositories.ConfigurationRepository;
import episen.back.Config.repositories.DeviceRepository;
import episen.back.Config.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public void assignConfigurationToDevice(Long deviceId, Long configurationId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id " + deviceId));
        Configuration configuration = configurationRepository.findById(configurationId)
                .orElseThrow(() -> new RuntimeException("Configuration not found with id " + configurationId));
        device.setConfiguration(configuration);
        deviceRepository.save(device);
    }
    public void assignDeviceToUser(Long deviceId, Long userId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id " + deviceId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        device.setUser(user);
        deviceRepository.save(device);
    }
    public List<Device> getDevicesByUserFullName(String fullName) {
        Optional<User> user = userRepository.findByFullName(fullName);
        if (user.isPresent()) {
            return deviceRepository.findByUser(user.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<Configuration> getConfigurationsByFullName(String fullName) {
        return userRepository.findByFullName(fullName)
                .map(user -> deviceRepository.findConfigurationsByUser(user))
                .orElseThrow(() -> new RuntimeException("User not found with fullName: " + fullName));
    }

}
