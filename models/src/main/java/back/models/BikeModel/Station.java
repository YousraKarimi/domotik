package back.models.BikeModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "station")
public class Station {
    @Id
    @Column(name="id_station")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "station",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Bike> bikes= new ArrayList<>();

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "station_location")
    private String stationLocation;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public Station(int capacity, String stationName, String stationLocation, double latitude, double longitude) {
        this.capacity = capacity;
        this.stationName = stationName;
        this.stationLocation = stationLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Station() {

    }
}
