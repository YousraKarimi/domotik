package back.models.BikeModel;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "itinerary")
public class Itinerary {

    @Id
    @Column(name="id_itinerary")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItenerary;

    @Column(name = "starting_point")
    private String startingPoint;

    @Column(name = "destination_point")
    private String destinationPoint;

    @Column(name = "estimated_distance")
    private double estimatedDistance;

    @Column(name = "estimated_duration")
    private int estimatedDuration;
}
