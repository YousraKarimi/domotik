package episen.CustomerAccount.services;


import back.models.CustomerAccountModel.*;
import back.models.Exceptions.InvoiceNotFoundException;
import back.models.Exceptions.SubscriptionNotFoundException;
import episen.CustomerAccount.respositories.InvoiceRepository;
import episen.CustomerAccount.respositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class DebtCalculationService {

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    private double updateTotalUnpaidForInvoice(Invoice invoice) {
        InvoiceDetails details = invoice.getInvoiceDetails();
        double totalUnpaid = calculateUnpaidForDetails(details);
        invoice.setTotalUnpaid(totalUnpaid);
        invoiceRepository.save(invoice);
        return totalUnpaid;
    }

    private double calculateUnpaidForDetails(InvoiceDetails details) {
        return details.getTotalAmount() - details.getAmountPaid();
    }

    public double calculateComplexFees(double totalUnpaid, int daysAfterdue) {
        double feeRate = calculateFeeRateBasedOnDays(daysAfterdue);
        double fees = totalUnpaid * feeRate;
        return fees;
    }

    private double calculateFeeRateBasedOnDays(int daysAfterDue) {
        if (daysAfterDue == 0 || (daysAfterDue > 0 && daysAfterDue < 3)) {
            return 0.0;
        } else if (daysAfterDue >= 3 && daysAfterDue <= 7) {
            return 0.1;
        } else {
            return 0.2;
        }
    }

    private long calculateDaysAfterDue(LocalDate today, Date Deadline) {
        LocalDate DeadlineDateLocal = Deadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(today, DeadlineDateLocal);
    }

    public double calculateTotalDebtForInvoice(Long invoiceId) {
        Invoice currentInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));

        Subscription subscription = currentInvoice.getSubscription();
        double totalDebt = 0.0;
        int currentIndex = subscription.getInvoices().indexOf(currentInvoice);
        for (int i = 0; i < currentIndex; i++) {
            Invoice previousInvoice = subscription.getInvoices().get(i);
            double previousTotalUnpaid = calculateUnpaidForDetails(previousInvoice.getInvoiceDetails());
            int daysAfterDue = (int) calculateDaysAfterDue(LocalDate.now(), previousInvoice.getInvoiceDetails().getDeadlinePaiement());
            double fees = calculateComplexFees(previousTotalUnpaid, daysAfterDue);

            totalDebt += fees + previousTotalUnpaid;

        }

        return totalDebt;
    }
    public double calculateTotalUnpaidFoSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        double totalUnpaidForSubscription = 0.0;

        for (Invoice invoice : subscription.getInvoices()) {
            updateTotalUnpaidForInvoice(invoice);
            InvoiceDetails invoiceDetails = invoice.getInvoiceDetails();
            LocalDate todayDate = LocalDate.now();
            Date deadline = invoiceDetails.getDeadlinePaiement();
            long daysAfterDue = calculateDaysAfterDue(todayDate, deadline);
            if (invoice.getTotalUnpaid() > 0) {
                double fees = calculateComplexFees(invoice.getTotalUnpaid(), (int) daysAfterDue);
                double TotalUnpaidwithfees = fees + invoice.getTotalUnpaid();
                invoice.setTotalUnpaid(TotalUnpaidwithfees);
                invoiceRepository.save(invoice);
                totalUnpaidForSubscription += invoice.getTotalUnpaid();

            }
        }
        return totalUnpaidForSubscription;
    }
}
