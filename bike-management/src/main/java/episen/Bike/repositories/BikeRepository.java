package episen.Bike.repositories;



import back.models.BikeModel.Bike;
import back.models.BikeModel.BikeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findBikeByAvailability(BikeAvailability bikeAvailability);

    @Query("SELECT s FROM Bike AS s WHERE s.availability = :availability")
    List<Bike> findBikeInStock(@Param("availability") BikeAvailability availability);
    @Query("SELECT b FROM Bike b WHERE b.availability = :availability")
    List<Bike> findParkedBikes(@Param("availability") BikeAvailability availability);

    List<Bike> findByStationIdAndAvailability(Long stationId, BikeAvailability availability);


}
