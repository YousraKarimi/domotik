package episen.CustomerAccount.controllers;


import back.models.CustomerAccountModel.InvoiceDetails;
import episen.CustomerAccount.respositories.InvoiceDetailsRepository;
import episen.CustomerAccount.services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Customer/invoiceDetails")
public class InvoiceDetailsController {
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    public InvoiceDetails addInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsRepository.save(invoiceDetails);
    }
    @GetMapping("/all")
    public ResponseEntity<List<InvoiceDetails>> findAllInvoiceDetails(){
        return new ResponseEntity<>(invoiceDetailsService.findAllInvoiceDetails(), HttpStatus.OK);
    }
    @GetMapping("/details")
    public ResponseEntity<List<InvoiceDetails>> getDetailsByDueDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate) {
        List<InvoiceDetails> details = invoiceDetailsService.getDetailsByDueDate(dueDate);
        return new ResponseEntity<>(details, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetails> findById(@PathVariable Long id){

        return new ResponseEntity<>(invoiceDetailsService.findByIdInvoiceDetails(id), HttpStatus.OK);
    }
    @GetMapping("/details/{invoiceId}")
    public ResponseEntity<InvoiceDetails> getDetailsByInvoiceId(@PathVariable Long invoiceId) {
        InvoiceDetails details = invoiceDetailsService.getDetailsByInvoiceId(invoiceId);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

}