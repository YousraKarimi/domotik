package episen.Bike.services;


import back.models.BikeModel.Itinerary;
import episen.Bike.repositories.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;
    public Itinerary addItinerary(Itinerary itinerary) {
        return itineraryRepository.save(itinerary);
    }

}
