package back.models.CustomerAccountModel;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "invoice")
public class Invoice {

    @Id
    @Column(name="id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;

    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
    private InvoiceDetails invoiceDetails;

    @ManyToOne
    @JoinColumn(name="id_subscription")
    private Subscription subscription;

    @Column(name = "subscription_code")
    private String subscriptionCode;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "total_unpaid")
    private double totalUnpaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_status")
    private InvoiceStatus invoiceStatus;

    public Invoice(Subscription subscription,String subscriptionCode, String invoiceNumber, double totalUnpaid, InvoiceStatus invoiceStatus) {
        this.subscriptionCode = subscriptionCode;
        this.invoiceNumber = invoiceNumber;
        this.subscription=subscription;
        this.totalUnpaid = totalUnpaid;
        this.invoiceStatus = invoiceStatus;
    }

    public Invoice() {

    }
    @Override
    public String toString() {
        return "Invoice{" +
                "idInvoice=" + idInvoice +
                ", subscriptionCode='" + subscriptionCode + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", totalUnpaid=" + totalUnpaid +
                ", invoiceStatus=" + invoiceStatus +
                '}';
    }
}