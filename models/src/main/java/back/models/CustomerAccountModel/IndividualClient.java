package back.models.CustomerAccountModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "individualclient")
public class IndividualClient extends  Customer {

    @Id
    @Column(name = "id_individualclient", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_individualclient;

    @OneToOne
    private CustomerAccount customerAccount;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;


    public IndividualClient(String firstName, String lastName, String gender, Date dateOfBirth, String customerAddress, String customerPhoneNumber, String customerEmail) {
        super(customerAddress, customerPhoneNumber, customerEmail);

        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public IndividualClient() {

    }
}



