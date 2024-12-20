package back.models.CustomerAccountModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "customer_account")
public class CustomerAccount  extends Utilisateur{

    @OneToOne
    @JsonIgnore
    private Subscription subscription;

    @OneToOne
    @JoinColumn(name="id_individualclient")
    private IndividualClient individualClient;

    @ManyToOne
    @JoinColumn(name="id_legalentity")
    private LegalEntity legalEntity;

    @Column(name = "account_creation_date")
    private Date accountCreationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    public CustomerAccount(String userName,String password,IndividualClient individualClient,  Date accountCreationDate, AccountStatus accountStatus) {
        super(userName,password);
        this.individualClient = individualClient;
        this.accountCreationDate = accountCreationDate;
        this.accountStatus = accountStatus;
    }

    public CustomerAccount( String userName,String password,LegalEntity legalEntity, Date accountCreationDate, AccountStatus accountStatus) {
        super(userName,password);
        this.legalEntity = legalEntity;
        this.accountCreationDate = accountCreationDate;
        this.accountStatus = accountStatus;
    }

    public CustomerAccount() {

    }
}
