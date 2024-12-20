package episen.CustomerAccount.controllers;



import back.models.CustomerAccountModel.Invoice;
import back.models.CustomerAccountModel.Subscription;
import episen.CustomerAccount.services.DebtCalculationService;
import episen.CustomerAccount.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("Customer/subscription")
public class SubscriptionController {

        @Autowired
        private SubscriptionService subscriptionService;
        @Autowired
        DebtCalculationService debtCalculationService;

        @GetMapping("/all")
        public ResponseEntity<List<Subscription>> findAllSubscription(){
            return new ResponseEntity<>(subscriptionService.findAllSubscription(), HttpStatus.OK);
        }

        @GetMapping("/getFirstName/{subscriptionId}")
        public ResponseEntity<String> getFirstNameBySubscriptionId(@PathVariable Long subscriptionId) {
            String firstName = subscriptionService.getClientFirstNameBySubscriptionId(subscriptionId);
            return new ResponseEntity<>(firstName, HttpStatus.OK);
        }

        @GetMapping("/getLastName/{subscriptionId}")
        public ResponseEntity<String> getLastNameBySubscriptionId(@PathVariable Long subscriptionId) {
            String lastName = subscriptionService.getClientLastNameBySubscriptionId(subscriptionId);
            return new ResponseEntity<>(lastName, HttpStatus.OK);
        }

        @GetMapping("/getSubscriptionCost/{invoiceId}")
        public ResponseEntity<Double> getSubscriptionCostForUnpaidInvoices(@PathVariable Long invoiceId) {
            Double subscriptionCost = subscriptionService.getSubscriptionCostByInvoiceId(invoiceId);
            return new ResponseEntity<>(subscriptionCost, HttpStatus.OK);
        }
        @GetMapping("/getSubscriptionCode/{invoiceId}")
        public ResponseEntity<String> getSubscriptionCodeForInvoices(@PathVariable Long invoiceId) {
            String subscriptionCode = subscriptionService.getSubscriptionCodeByInvoiceId(invoiceId);
            return new ResponseEntity<>(subscriptionCode, HttpStatus.OK);
        }
        @GetMapping("/calculateTotalUnpaidforSubscription/{subscriptionId}")
        public ResponseEntity<Double> calculateTotalUnpaidforSunbscription(@PathVariable Long subscriptionId) {
            double totalUnpaid = debtCalculationService.calculateTotalUnpaidFoSubscription(subscriptionId);
            return new ResponseEntity<>(totalUnpaid, HttpStatus.OK);
        }
        @GetMapping("/InvoicesForSubscription/{subscriptionId}")
        public ResponseEntity<List<Invoice>> getInvoicesForSubscription(@PathVariable Long subscriptionId) {
            List<Invoice> Invoices = subscriptionService.getInvoicesForSubscription(subscriptionId);
            return new ResponseEntity<>(Invoices, HttpStatus.OK);
        }
        @GetMapping("/subscriptionsWithUnpaidInvoices")
        public ResponseEntity<List<Subscription>> getSubscriptionsWithUnpaidInvoices() {
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsWithUnpaidInvoices();
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        }
        @GetMapping("/calculateTotalDebtForInvoice/{invoiceId}")
        public ResponseEntity<Double> calculateTotalDebtForInvoice(@PathVariable Long invoiceId) {
            double totalUnpaid = debtCalculationService.calculateTotalDebtForInvoice(invoiceId);
            return new ResponseEntity<>(totalUnpaid, HttpStatus.OK);
        }

    }
