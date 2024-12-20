package back.models.BikeModel;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {

    @Id
    @Column(name="id_delivery")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

//    @ManyToOne
//    @JoinColumn(name="id_supplier")
//    private Supplier supplier;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "delivery_code")
    private String deliveryCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryState")
    private DeliveryState deliveryState;

//    public Delivery(Supplier supplier, LocalDate deliveryDate, String deliveryCode, DeliveryState deliveryState) {
//        this.supplier = supplier;
//        this.deliveryDate = deliveryDate;
//        this.deliveryCode = deliveryCode;
//        this.deliveryState = deliveryState;
//    }

    public Delivery() {

    }
}
