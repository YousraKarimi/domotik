package back.models.CustomerAccountModel;

import lombok.Data;

import jakarta.persistence.*;

@MappedSuperclass
@Data
public abstract class Customer {

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(name = "customer_email")
    private String customerEmail;

    public Customer(String customerAddress, String customerPhoneNumber, String customerEmail) {
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
    }

    public Customer() {

    }
}
