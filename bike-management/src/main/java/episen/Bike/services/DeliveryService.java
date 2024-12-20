package episen.Bike.services;



import back.models.BikeModel.Delivery;
import episen.Bike.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    public Delivery addDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }


}
