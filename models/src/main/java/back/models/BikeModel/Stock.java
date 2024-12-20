package back.models.BikeModel;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name="id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @OneToMany(mappedBy = "stock",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Bike> bikes= new ArrayList<>();

    @Column(name = "location")
    private String location;

    @Column(name = "quantity_available")
    private int quantityAvailable;

}
