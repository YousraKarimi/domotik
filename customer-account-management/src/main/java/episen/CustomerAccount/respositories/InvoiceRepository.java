package episen.CustomerAccount.respositories;



import back.models.CustomerAccountModel.IndividualClient;
import back.models.CustomerAccountModel.Invoice;
import back.models.CustomerAccountModel.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {



    @Query("SELECT DISTINCT i.subscription FROM Invoice i WHERE i.totalUnpaid <> 0")
    List<Subscription> findSubscriptionsWithUnpaidInvoices();

    @Query("SELECT i FROM Invoice i WHERE i.subscription.idSubscription = :subscriptionId AND i.totalUnpaid <> 0")
    List<Invoice> findInvoiceUNRESOLVEDFofSubscription(@Param("subscriptionId") Long subscriptionId);


    @Query("SELECT i FROM Invoice i " +
            "WHERE LOWER(i.subscription.customerAccount.individualClient.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) " +
            "OR LOWER(i.subscription.customerAccount.individualClient.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Invoice> findByCustomerName(@Param("firstName") String firstName, @Param("lastName") String lastName);


    @Query("SELECT i FROM Invoice i " +
            "WHERE i.subscription.customerAccount.userName = :userName")
    List<Invoice> findByCustomerUserName(@Param("userName") String userName);
    @Query("SELECT s.customerAccount.individualClient FROM Invoice i JOIN i.subscription s WHERE i.idInvoice = :invoiceId")
    IndividualClient findIndividualClientByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT id.dueDate FROM InvoiceDetails id WHERE id.invoice.idInvoice = :invoiceId")
    Date findDueDateByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT id.deadlinePaiement FROM InvoiceDetails id WHERE id.invoice.idInvoice = :invoiceId")
    Date findDeadlinePaiementByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT ic.customerPhoneNumber FROM Invoice i JOIN i.subscription.customerAccount.individualClient ic WHERE i.idInvoice = :invoiceId")
    String findPhoneNumberByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT ic.customerAddress FROM Invoice i JOIN i.subscription.customerAccount.individualClient ic WHERE i.idInvoice = :invoiceId")
    String findCustomerAddressByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT ic.customerEmail FROM Invoice i JOIN i.subscription.customerAccount.individualClient ic WHERE i.idInvoice = :invoiceId")
    String findCustomerEmailByInvoiceId(@Param("invoiceId") Long invoiceId);

}
