package back.models.CustomerAccountModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(name="id_subscription")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubscription;

    @OneToMany(mappedBy = "subscription",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Invoice> invoices= new ArrayList<>();

    @OneToOne
    @JoinColumn(name="id_user")
    private CustomerAccount customerAccount;

    @Column(name="code_subscription")
    private String codeSubscription;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "subscription_cost")
    private double subscriptionCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_state")
    private SubscrptionState subscriptionState;
    public Subscription(String codeSubscription,CustomerAccount customerAccount, Date startDate, Date endDate, double subscriptionCost, SubscriptionType subscriptionType, SubscrptionState subscriptionState) {
        this.customerAccount = customerAccount;
        this.startDate = startDate;
        this.codeSubscription=codeSubscription;
        this.endDate = endDate;
        this.subscriptionCost = subscriptionCost;
        this.subscriptionType = subscriptionType;
        this.subscriptionState = subscriptionState;
    }

    public Subscription() {

    }

    @Override
    public String toString() {
        return "Subscription{" +
                "idSubscription=" + idSubscription +
                ", codeSubscription='" + codeSubscription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", subscriptionCost=" + subscriptionCost +
                ", subscriptionType=" + subscriptionType +
                ", subscriptionState=" + subscriptionState +
                '}';
    }
}
