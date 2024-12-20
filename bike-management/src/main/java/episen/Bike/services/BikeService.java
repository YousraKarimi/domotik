package episen.Bike.services;



import back.models.BikeModel.Bike;
import back.models.BikeModel.BikeAvailability;
import back.models.BikeModel.BikeState;
import back.models.BikeModel.Station;
import episen.Bike.repositories.BikeRepository;
import episen.Bike.repositories.StationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private StationRepository stationRepository;


    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> findAllBike(){
        return bikeRepository.findAll();
    }
    public Bike findByIdBike(Long idBike) {
        Optional<Bike> optionalBike = bikeRepository.findById(idBike);
        return optionalBike.orElse(null);
    }



    public List<Bike> getBikesInStock(){ return bikeRepository.findBikeInStock(BikeAvailability.IN_STOCK);}
    public List<Bike> getBikesInCirculation(){ return bikeRepository.findBikeByAvailability(BikeAvailability.IN_CIRCULATION);}


    public List<Bike> getParkedBikes(){ return bikeRepository.findParkedBikes(BikeAvailability.PARKED);}

    public List<Bike> getAvailableBikesInStation(Long stationId) {
        return bikeRepository.findByStationIdAndAvailability(stationId, BikeAvailability.PARKED);
    }
    public List<Map.Entry<String, Integer>> getNumberOfBikes() {
        List<Map.Entry<String, Integer>> number_bikes = new ArrayList<>();
        List<Station> stations = stationRepository.findAll();

        for (Station station : stations) {
            List<Bike> bikes = getAvailableBikesInStation(station.getId());
            number_bikes.add(new AbstractMap.SimpleEntry<>(station.getStationName(), bikes.size()));
        }

        return number_bikes;
    }

    public void generateRandomBikeData(int numberOfBikes) {

        Random random = new Random();

        for (int i = 0; i < numberOfBikes; i++) {
            Station randomStation = generateStation();
            String serialNumber = generateSerialNumber();
            double distanceTraveled = Math.round(random.nextDouble() * 1000.0 * 1000.0) / 1000.0; //distance in kilometers
            BikeAvailability availability = BikeAvailability.PARKED;//.values()[random.nextInt(BikeAvailability.values().length)];
            BikeState bikeState = BikeState.values()[random.nextInt(BikeState.values().length)];

            Bike newBike = new Bike(randomStation, serialNumber, distanceTraveled, availability, bikeState, null);

            bikeRepository.save(newBike);
        }
    }

    private Station generateStation() {
        List<Station> stations = stationRepository.findAll();
        Random random = new Random();

        // StationCounts is a map to keep track of the count of bikes for each station
        Map<Long, Integer> stationCounts = new HashMap<>();
        Long randomStationId;
        do {
            randomStationId = stations.get(random.nextInt(stations.size())).getId();
        } while (stationCounts.getOrDefault(randomStationId, 0) > 15);

        // Increment the count for the selected station
        stationCounts.put(randomStationId, stationCounts.getOrDefault(randomStationId, 0) + 1);

        return stations.get(random.nextInt(stations.size()));
    }

    // Generate a serial number (combination of letters and numbers) of length 8
    private String generateSerialNumber() {
        Random random = new Random();
        StringBuilder serialNumber = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i < 2) {
                char randomLetter = (char) ('A' + random.nextInt(26));
                serialNumber.append(randomLetter);
            } else {
                int randomNumber = random.nextInt(10);
                serialNumber.append(randomNumber);
            }        }
        return serialNumber.toString();
    }

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void updateBikeDataRegularly() {
        releaseBike();
    }

    public void releaseBike() {
        double stayParkedProb = 0.2;
        double parkedToCirculationProb = 0.8;
        List<Double> probab = new ArrayList<>();
        probab.add(stayParkedProb);
        probab.add(parkedToCirculationProb);
        List<String> availabilities = new ArrayList<>();
        availabilities.add(BikeAvailability.PARKED.toString());
        availabilities.add(BikeAvailability.IN_CIRCULATION.toString());
        double stayCirculationProb = 0.5;
        double circulationToParkedProb = 0.5;
        List<Double> probab1 = List.of(stayCirculationProb, circulationToParkedProb);

        List<Bike> parkedBikes = getParkedBikes();
        for (Bike bike : parkedBikes) {
            String bikeAvail = getRandomString(availabilities, probab);
            bike.setAvailability(BikeAvailability.valueOf(bikeAvail));
            if(bikeAvail.equals(BikeAvailability.IN_CIRCULATION.toString())){
                bike.setTimeEnteredInCirculation(LocalTime.now());
            }
            bikeRepository.save(bike);
        }

        List<Bike> bikesInCirculation = getBikesInCirculation();
        for (Bike bike : bikesInCirculation) {
            String bikeAvail1 = getRandomString(availabilities, probab1);
            bike.setAvailability(BikeAvailability.valueOf(bikeAvail1));
            if(bikeAvail1.equals(BikeAvailability.PARKED.toString())){
                List<Station> stations=stationRepository.findAll();
                List<Station> filteredStations = stations.stream()
                        .filter(station -> !station.equals(bike.getStation()))
                        .collect(Collectors.toList());
                Random random = new Random();
                bike.setStation(filteredStations.get(random.nextInt(filteredStations.size())));
            }
            bikeRepository.save(bike);
        }
    }

    public String getRandomString(List<String> strings, List<Double> probabilities) {
        Random random = new Random();
        double randomValue = random.nextDouble();

        double cumulativeProbability = 0.0;
        for (int i = 0; i < strings.size(); i++) {
            cumulativeProbability += probabilities.get(i);
            if (randomValue <= cumulativeProbability) {
                return strings.get(i);
            }
        }
        return strings.get(strings.size() - 1);


    }
}

