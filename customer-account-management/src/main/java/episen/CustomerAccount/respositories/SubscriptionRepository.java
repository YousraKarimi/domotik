package episen.CustomerAccount.respositories;


import back.models.CustomerAccountModel.Invoice;
import back.models.CustomerAccountModel.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT s FROM Subscription s JOIN FETCH s.invoices WHERE s.idSubscription = :subscriptionId")
    Subscription getSubscriptionWithInvoices(@Param("subscriptionId") Long subscriptionId);
    @Query("SELECT c.firstName FROM Subscription s INNER JOIN s.customerAccount.individualClient c WHERE s.idSubscription = :subscriptionId")
    String findClientFirstNameBySubscriptionId(@Param("subscriptionId") Long subscriptionId);
    @Query("SELECT c.lastName FROM Subscription s INNER JOIN s.customerAccount.individualClient c WHERE s.idSubscription = :subscriptionId")
    String findClientLastNameBySubscriptionId(@Param("subscriptionId") Long subscriptionId);
    @Query("SELECT DISTINCT subscription FROM Invoice ")
    List<Subscription> findSubscriptionsWithUnpaidInvoices();
    //i WHERE i.totalUnpaid <> 0
    @Query("SELECT i FROM Invoice i WHERE i.subscription.idSubscription = :subscriptionId ")
    List<Invoice> findInvoiceForSubscription(@Param("subscriptionId") Long subscriptionId);
    @Query("SELECT s.subscriptionCost FROM Invoice i JOIN i.subscription s WHERE i.idInvoice = :invoiceId")
    Double findSubscriptionCostByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT s.codeSubscription FROM Invoice i JOIN i.subscription s WHERE i.idInvoice = :invoiceId")
    String findSubscriptionCodeByInvoiceId(@Param("invoiceId") Long invoiceId);
    @Query("SELECT i.subscription.idSubscription FROM Invoice i WHERE i.idInvoice = :invoiceId")
    Long findSubscriptionIdByInvoiceId(@Param("invoiceId") Long invoiceId);



}
