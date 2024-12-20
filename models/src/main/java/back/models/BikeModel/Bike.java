package back.models.BikeModel;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Table(name = "bike")
public class Bike {

    @Id
    @Column(name="id_bike")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBike;

    @ManyToOne
    @JoinColumn(name="id_station")
    private Station station;

    @ManyToOne
    @JoinColumn(name="id_stock")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name="id_demand")
    private Demand demand;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "distance_traveled")
    private double distanceTraveled;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private BikeAvailability availability;

    @Enumerated(EnumType.STRING)
    @Column(name = "bike_state")
    private BikeState bikeState;

    @Column(name = "time")
    private LocalTime timeEnteredInCirculation;

    public Bike(Long idBike, Station station, Stock stock, Demand demand, Date purchaseDate, String serialNumber, double distanceTraveled, BikeAvailability availability, BikeState bikeState, LocalTime timeEnteredInCirculation) {
        this.idBike = idBike;
        this.station = station;
        this.stock = stock;
        this.demand = demand;
        this.purchaseDate = purchaseDate;
        this.serialNumber = serialNumber;
        this.distanceTraveled = distanceTraveled;
        this.availability = availability;
        this.bikeState = bikeState;
        this.timeEnteredInCirculation = timeEnteredInCirculation;
    }

    public Bike() {

    }

    public Bike(Station station, String serialNumber, double distanceTraveled, BikeAvailability availability, BikeState bikeState, LocalTime timeEnteredInCirculation) {
        this.station = station;
        this.serialNumber = serialNumber;
        this.distanceTraveled = distanceTraveled;
        this.availability = availability;
        this.bikeState = bikeState;
        this.timeEnteredInCirculation = timeEnteredInCirculation;
    }
}

