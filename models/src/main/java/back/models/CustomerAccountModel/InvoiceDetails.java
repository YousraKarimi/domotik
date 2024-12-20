package back.models.CustomerAccountModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "invoicedetails")
public class InvoiceDetails {


    @Id
    @Column(name = "id_details")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetails;

    @OneToOne
    @JoinColumn(name="id_invoice")
    @JsonIgnore
    private Invoice invoice;

    @Column(name = "number_of_subscriptions")
    private int numberOfSubscriptions;

    @Column(name = "invoice_number")
    private String idInvoice;

    @Column(name = "amount_paid")
    private double amountPaid;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "deadline_paiement")
    private Date deadlinePaiement ;

    public InvoiceDetails(String idInvoice,Invoice invoice, int numberOfSubscriptions, double amountPaid, double totalAmount, Date dueDate, Date deadlinePaiement) {
        this.invoice = invoice;
        this.idInvoice=idInvoice;
        this.numberOfSubscriptions = numberOfSubscriptions;
        this.amountPaid = amountPaid;
        this.totalAmount =totalAmount;
        this.dueDate = dueDate;
        this.deadlinePaiement=deadlinePaiement;
    }

    public InvoiceDetails() {

    }

    @Override
    public String toString() {
        return "Invoice Détails{" +
                "invoice=" + invoice +
                ", idInvoice='" + idInvoice + '\'' +
                ", numberOfSubscriptions='" + numberOfSubscriptions + '\'' +
                ", amountPaid=" + amountPaid +
                ", totalAmount=" + totalAmount +
                ", dueDate=" + dueDate +
                ", deadlinePaiement=" + deadlinePaiement +
                '}';
    }
}