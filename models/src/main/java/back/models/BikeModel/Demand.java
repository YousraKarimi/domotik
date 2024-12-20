package back.models.BikeModel;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "demand")
public class Demand {
    @Id
    @Column(name="id_demand")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

//    @ManyToOne
//    @JoinColumn(name="id_supplier")
//    private Supplier supplier;

    @OneToMany(mappedBy = "demand",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Bike> newbikes = new ArrayList<>();

    @Column(name = "demand_date")
    private Date orderDate;

    @Column(name = "demand_number")
    private String orderNumber;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "demand_description")
    private String orderDescription;

//    public Demand(Supplier supplier, List<Bike> newbikes, Date orderDate, String orderNumber, int quantity, String orderDescription) {
//        this.supplier = supplier;
//        this.newbikes = newbikes;
//        this.orderDate = orderDate;
//        this.orderNumber = orderNumber;
//        this.quantity = quantity;
//        this.orderDescription = orderDescription;
//    }

    public Demand() {

    }
}
