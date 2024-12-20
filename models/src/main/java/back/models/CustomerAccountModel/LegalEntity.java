package back.models.CustomerAccountModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "legalentity")
public class LegalEntity extends Customer{

    @Id
    @Column(name="id_legal_entity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLegalEntity;

    @OneToMany(mappedBy = "legalEntity",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonIgnore
    private List<CustomerAccount> customerAccounts= new ArrayList<>();

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "sector")
    private String sector;



}
