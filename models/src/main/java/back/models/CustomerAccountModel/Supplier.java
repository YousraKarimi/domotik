package back.models.CustomerAccountModel;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name="id_supplier")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;

//    @Column(name="demands")
//    @OneToMany(mappedBy = "supplier",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    private List<Demand> demands = new ArrayList<>();
//
//    @OneToMany(mappedBy = "supplier",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    private List<Delivery> deliveries = new ArrayList<>();

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "supplier_adress")
    private String supplierAdress;

    @Column(name = "supplier_phoneNumber")
    private String supplierPhoneNumber;

    public Supplier(String supplierName, String supplierEmail, String supplierAdress, String supplierPhoneNumber) {
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierAdress = supplierAdress;
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public Supplier() {

    }
}
