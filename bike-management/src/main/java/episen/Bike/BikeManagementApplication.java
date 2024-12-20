package episen.Bike;

import back.models.BikeModel.Station;
import episen.Bike.services.BikeService;
import episen.Bike.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"back.models"})
public class BikeManagementApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(BikeManagementApplication.class);

    @Autowired
    private StationService stationService;

    @Autowired
    private BikeService bikeService;

    public static void main(String[] args) {
        SpringApplication.run(BikeManagementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Starting application...");
        generateStation();
        generateBikes();
        bikeService.updateBikeDataRegularly();
        logger.info("Application finished processing.");
    }

    private void generateStation() {
        Station station1 = new Station(30, "STATION 1", "Cambon - Rivoli, 75001 Paris", 48.86752602000076, 2.3253922197807255);
        stationService.addStation(station1);
        Station station2 = new Station(30, "STATION 2", "Place de la République - Temple, 75003 Paris", 48.868063154556395, 2.3632603634006815);
        stationService.addStation(station2);
        Station station3 = new Station(30, "STATION 3", "Place Saint-André des Arts, 75006 Paris", 48.85429726238369, 2.342846269805018);
        stationService.addStation(station3);
    }

    private void generateBikes() {
        bikeService.generateRandomBikeData(30);
    }
}
