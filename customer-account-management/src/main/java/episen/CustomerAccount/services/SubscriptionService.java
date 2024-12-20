package episen.CustomerAccount.services;


import back.models.CustomerAccountModel.Invoice;
import back.models.CustomerAccountModel.Subscription;
import back.models.Exceptions.ResourceNotFoundException;
import episen.CustomerAccount.respositories.InvoiceRepository;
import episen.CustomerAccount.respositories.SubscriptionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public String getClientFirstNameBySubscriptionId(Long subscriptionId) {
        return subscriptionRepository.findClientFirstNameBySubscriptionId(subscriptionId);
    }
    public String getClientLastNameBySubscriptionId(Long subscriptionId) {
        return subscriptionRepository.findClientLastNameBySubscriptionId(subscriptionId);
    }
    public List<Subscription> findAllSubscription(){
        return subscriptionRepository.findAll();
    }
    public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }
    public void generateInvoices() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusMonths(1);
        List<Subscription> subscriptions = subscriptionRepository.findByStartDateBetween(startDate, currentDate);
        for (Subscription subscription : subscriptions) {
            Invoice invoice = new Invoice();
            invoice.setSubscription(subscription);
            invoiceRepository.save(invoice);
        }
    }
    public void assignInvoiceToSubscription(Long subscriptionId, Long invoiceId) {
        Subscription subscription = subscriptionRepository.getSubscriptionWithInvoices(subscriptionId);

        if (subscription == null) {
            throw new ResourceNotFoundException("Subscription not found with id " + subscriptionId);
        }

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));

        Hibernate.initialize(subscription.getInvoices());
        subscription.getInvoices().add(invoice);
    }
    public Subscription getSubscriptionWithInvoices(Long subscriptionId) {
        return subscriptionRepository.getSubscriptionWithInvoices(subscriptionId);
    }
    public Double getSubscriptionCostByInvoiceId(Long invoiceId) {
        return subscriptionRepository.findSubscriptionCostByInvoiceId(invoiceId);
    }
    public String getSubscriptionCodeByInvoiceId(Long invoiceId) {
        return subscriptionRepository.findSubscriptionCodeByInvoiceId(invoiceId);
    }
    public List<Subscription> getSubscriptionsWithUnpaidInvoices() {
        return subscriptionRepository.findSubscriptionsWithUnpaidInvoices();
    }
    public List<Invoice> getInvoicesForSubscription(Long subscriptionId) {
        return subscriptionRepository.findInvoiceForSubscription(subscriptionId);
    }


}