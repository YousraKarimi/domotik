package episen.back.Config;

import episen.back.Config.models.Configuration;
import episen.back.Config.models.Device;
import episen.back.Config.models.User;
import episen.back.Config.repositories.ConfigurationRepository;
import episen.back.Config.repositories.DeviceRepository;
import episen.back.Config.repositories.UserRepository;
import episen.back.Config.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "episen.back.Config.models")
public class House_ConfigApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(House_ConfigApplication.class, args);
    }
    private static final Logger logger = LoggerFactory.getLogger(House_ConfigApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private DeviceService deviceService;


    @Override
    public void run(String... args) {
        logger.info("Starting application...");
        if (isNotLoaded()){
            loadData();
        }
        logger.info("Application finished processing.");
    }

    public boolean isNotLoaded(){
        return userRepository.findByFullName("yousraKarimi").isEmpty();
    }

    public void loadData() {
        User userY = new User("yousraKarimi","yousraKarimi@gmail.com","yousra","yousra");
        userRepository.save(userY);
        User userD = new User("Donatien","Donatien@gmail.com","donatien","donatien");
        userRepository.save(userD);


        Configuration configLight = new Configuration("Living Room Light Config", true, "Automatic", "18:00-23:00");
        Configuration configWindow = new Configuration("Living Room Window Config", true, "Automatic", "18:00-23:00");
        Configuration configDoor = new Configuration("Garage Door Config", false, "Manual", "06:00-22:00");
        Configuration configFan = new Configuration("Bedroom Fan Config", true, "Automatic", "20:00-06:00");
        Configuration configLock = new Configuration("Front Door Lock Config", true, "Smart", "21:00-06:00");
        Configuration configBlinds = new Configuration("Bedroom Blinds Config", true, "Automatic", "07:00-19:00");


        configurationRepository.saveAll(List.of(configLight, configWindow, configDoor, configFan, configLock,
                configBlinds));




        Device light = new Device("Living Room Light", "Light", "ON");
        Device window = new Device("Living Room Window", "Window", "CLOSED");
        Device door = new Device("Garage Door", "Door", "OPEN");
        Device ac = new Device("Living Room AC", "AC", "ON");
        Device camera = new Device("Security Camera", "Camera", "ACTIVE");

        Device thermostat = new Device("Thermostat", "Thermostat", "ON");
        Device heater = new Device("Bathroom Heater", "Heater", "OFF");
        Device sprinkler = new Device("Garden Sprinkler", "Sprinkler", "OFF");
        Device fridge = new Device("Kitchen Fridge", "Fridge", "ON");
        Device tv = new Device("Living Room TV", "TV", "STANDBY");

        Device fan = new Device("Bedroom Fan", "Fan", "ON");
        Device lock = new Device("Front Door Lock", "Lock", "LOCKED");
        Device blinds = new Device("Bedroom Blinds", "Blinds", "OPEN");
        Device washer = new Device("Laundry Washer", "Washer", "OFF");
        Device oven = new Device("Kitchen Oven", "Oven", "OFF");

        Device alarm = new Device("Home Alarm", "Alarm", "ACTIVE");
        Device garageLight = new Device("Garage Light", "Light", "ON");
        Device vacuum = new Device("Vacuum Cleaner", "Vacuum", "OFF");
        Device speaker = new Device("Smart Speaker", "Speaker", "ON");
        Device pool = new Device("Swimming Pool Heater", "Heater", "OFF");

        deviceRepository.saveAll(List.of(light, window, door, ac, camera, thermostat, heater,
                sprinkler, fridge, tv, fan, lock, blinds, washer, oven,
                alarm, garageLight, vacuum, speaker, pool));




        /////////////////////////////////////////////Yousra//////////////////////////////////////////////////
        deviceService.assignDeviceToUser(light.getId(),userY.getId());
        deviceService.assignDeviceToUser(window.getId(),userY.getId());
        deviceService.assignDeviceToUser(door.getId(),userY.getId());
        deviceService.assignDeviceToUser(ac.getId(),userY.getId());
        deviceService.assignDeviceToUser(camera.getId(),userY.getId());
        deviceService.assignDeviceToUser(thermostat.getId(),userY.getId());
        deviceService.assignDeviceToUser(heater.getId(),userY.getId());
        deviceService.assignDeviceToUser(sprinkler.getId(),userY.getId());
        deviceService.assignDeviceToUser(fridge.getId(),userY.getId());
        deviceService.assignDeviceToUser(tv.getId(),userY.getId());

        deviceService.assignConfigurationToDevice(light.getId(),configLight.getId());
        deviceService.assignConfigurationToDevice(window.getId(),configWindow.getId());
        deviceService.assignConfigurationToDevice(door.getId(),configDoor.getId());


        ///////////////////////////////////////////Donatien///////////////////////////////////////////////////
        deviceService.assignDeviceToUser(fan.getId(),userD.getId());
        deviceService.assignDeviceToUser(lock.getId(),userD.getId());
        deviceService.assignDeviceToUser(blinds.getId(),userD.getId());
        deviceService.assignDeviceToUser(washer.getId(),userD.getId());
        deviceService.assignDeviceToUser(oven.getId(),userD.getId());
        deviceService.assignDeviceToUser(alarm.getId(),userD.getId());
        deviceService.assignDeviceToUser(garageLight.getId(),userD.getId());
        deviceService.assignDeviceToUser(vacuum.getId(),userD.getId());
        deviceService.assignDeviceToUser(speaker.getId(),userD.getId());
        deviceService.assignDeviceToUser(pool.getId(),userD.getId());

        deviceService.assignConfigurationToDevice(fan.getId(),configFan.getId());
        deviceService.assignConfigurationToDevice(lock.getId(),configLock.getId());
        deviceService.assignConfigurationToDevice(blinds.getId(),configBlinds.getId());


    }
}
