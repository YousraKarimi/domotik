package episen.CustomerAccount.controllers;


import back.models.CustomerAccountModel.IndividualClient;
import back.models.CustomerAccountModel.Invoice;
import back.models.CustomerAccountModel.InvoiceDetails;
import episen.CustomerAccount.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("Customer/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> findAllInvoice(){
        return new ResponseEntity<>(invoiceService.findAllInvoice(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id){
        return new ResponseEntity<>(invoiceService.findByIdInvoice(id), HttpStatus.OK);
    }
    @GetMapping("/details/byDueDate")
    public ResponseEntity<List<Invoice>> getInvoicesByDueDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate) {
        List<InvoiceDetails> details = invoiceService.getDetailsByDueDate(dueDate);
        List<Invoice> invoices = details.stream()
                .map(InvoiceDetails::getInvoice)
                .collect(Collectors.toList());

        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Invoice>> findByClientName(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        List<Invoice> invoices = invoiceService.getInvoicesByCustomerName(firstName, lastName);

        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/getDueDateByInvoiceId/{invoiceId}")
    public ResponseEntity<Date> getDueDateByInvoiceId(@PathVariable Long invoiceId) {
        Date dueDate = invoiceService.getDueDateByInvoiceId(invoiceId);
        return new ResponseEntity<>(dueDate, HttpStatus.OK);
    }

    @GetMapping("/getDeadlinePaiementByInvoiceId/{invoiceId}")
    public ResponseEntity<Date> getDeadlinePaiementByInvoiceId(@PathVariable Long invoiceId) {
        Date deadlinePaiement = invoiceService.getDeadlinePaiementByInvoiceId(invoiceId);
        return new ResponseEntity<>(deadlinePaiement, HttpStatus.OK);
    }

    @GetMapping("/getLastNameIndividualClientByInvoiceId/{invoiceId}")
    public ResponseEntity<String> getLastNameIndividualClientByInvoiceId(@PathVariable Long invoiceId) {
        IndividualClient individualClient = invoiceService.getIndividualClientByInvoiceId(invoiceId);
        String LastName = individualClient.getLastName();
        return new ResponseEntity<>(LastName, HttpStatus.OK);
    }

    @GetMapping("/getFirstNameIndividualClientByInvoiceId/{invoiceId}")
    public ResponseEntity<String> getFirstNameIndividualClientByInvoiceId(@PathVariable Long invoiceId) {
        IndividualClient individualClient = invoiceService.getIndividualClientByInvoiceId(invoiceId);
        String FirstName = individualClient.getFirstName();
        return new ResponseEntity<>(FirstName, HttpStatus.OK);
    }

    @GetMapping("/getPhoneNumber/{invoiceId}")
    public ResponseEntity<String> getPhoneNumberByInvoiceId(@PathVariable Long invoiceId) {
        String phoneNumber = invoiceService.getPhoneNumberByInvoiceId(invoiceId);
        return new ResponseEntity<>(phoneNumber, HttpStatus.OK);
    }

    @GetMapping("/getCustomerAddress/{invoiceId}")
    public ResponseEntity<String> getCustomerAddressByInvoiceId(@PathVariable Long invoiceId) {
        String customerAddress = invoiceService.getCustomerAddressByInvoiceId(invoiceId);
        return new ResponseEntity<>(customerAddress, HttpStatus.OK);
    }
    @GetMapping("/getCustomerEmail/{invoiceId}")
    public ResponseEntity<String> getCustomerEmailByInvoiceId(@PathVariable Long invoiceId) {
        String customerEmail = invoiceService.getCustomerEmailByInvoiceId(invoiceId);
        return new ResponseEntity<>(customerEmail, HttpStatus.OK);
    }

    @GetMapping("/getPeriodOfInvoice/{invoiceId}")
    public ResponseEntity<String> getFormattedDueDate(@PathVariable Long invoiceId) {
        String formattedDueDate = invoiceService.getFormattedDueDate(invoiceId);
        return new ResponseEntity<>(formattedDueDate, HttpStatus.OK);
    }
    @GetMapping("/searchByUserName")
    public ResponseEntity<List<Invoice>> findByCustomerUserName(
            @RequestParam(required = false) String userName) {
        if (userName == null || userName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Invoice> invoices = invoiceService.getInvoicesByCustomerUserName(userName);

        return ResponseEntity.ok(invoices);
    }
}