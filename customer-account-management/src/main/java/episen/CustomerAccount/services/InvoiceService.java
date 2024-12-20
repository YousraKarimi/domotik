package episen.CustomerAccount.services;


import back.models.CustomerAccountModel.IndividualClient;
import back.models.CustomerAccountModel.Invoice;
import back.models.CustomerAccountModel.InvoiceDetails;
import back.models.CustomerAccountModel.Subscription;
import back.models.Exceptions.ResourceNotFoundException;
import episen.CustomerAccount.respositories.InvoiceDetailsRepository;
import episen.CustomerAccount.respositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    public Invoice addInvoice(Invoice invoice) {return invoiceRepository.save(invoice);}
    public void assignInvoiceDetailsToInvoice(Long invoiceId, Long invoiceDetailsId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));

        InvoiceDetails invoiceDetails = invoiceDetailsRepository.findById(invoiceDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceDetails not found with id " + invoiceDetailsId));

        invoice.setInvoiceDetails(invoiceDetails);
        invoiceRepository.save(invoice);
    }

    public List<Subscription> getSubscriptionsWithUnpaidInvoices() {
        return invoiceRepository.findSubscriptionsWithUnpaidInvoices();
    }

    // Dans InvoiceService
    public List<Invoice> getUnresolvedInvoicesForSubscription(Long subscriptionId) {
        return invoiceRepository.findInvoiceUNRESOLVEDFofSubscription(subscriptionId);
    }
    public List<Invoice> findAllInvoice(){
        return invoiceRepository.findAll();
}

    public Invoice findByIdInvoice(Long idInvoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(idInvoice);
        return optionalInvoice.orElse(null);
    }
    public List<InvoiceDetails> getDetailsByDueDate(Date dueDate) {
        return invoiceDetailsRepository.findByDueDate(dueDate);
    }
    public List<Invoice> getInvoicesByCustomerName(String firstName, String lastName) {
        return invoiceRepository.findByCustomerName(firstName, lastName);
    }

    public List<Invoice> getInvoicesByCustomerUserName(String userName) {
        return invoiceRepository.findByCustomerUserName(userName);
    }
//    public List<Invoice> getInvoiceUNRESOLVED(InvoiceStatus type){
//        return invoiceRepository.findInvoiceUNRESOLVED(type);}

    public Date getDueDateByInvoiceId(Long invoiceId) {
        InvoiceDetails invoiceDetails = invoiceDetailsRepository.findByInvoiceId(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceDetails not found for invoice with id " + invoiceId));

        return invoiceRepository.findDueDateByInvoiceId(invoiceId);
    }
    public Date getDeadlinePaiementByInvoiceId(Long invoiceId) {
        InvoiceDetails invoiceDetails = invoiceDetailsRepository.findByInvoiceId(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("InvoiceDetails not found for invoice with id " + invoiceId));

        return invoiceRepository.findDeadlinePaiementByInvoiceId(invoiceId);
    }
    public IndividualClient getIndividualClientByInvoiceId(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + invoiceId));
        return invoiceRepository.findIndividualClientByInvoiceId(invoiceId);
    }
    public String getPhoneNumberByInvoiceId(Long invoiceId) {
        return invoiceRepository.findPhoneNumberByInvoiceId(invoiceId);
    }
    public String getCustomerAddressByInvoiceId(Long invoiceId) {
        return invoiceRepository.findCustomerAddressByInvoiceId(invoiceId);
    }
    public String getCustomerEmailByInvoiceId(Long invoiceId) {
        return invoiceRepository.findCustomerEmailByInvoiceId(invoiceId);
    }
    public String getFormattedDueDate(Long invoiceId) {
        Date dueDate = invoiceRepository.findDueDateByInvoiceId(invoiceId);
        if (dueDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM YYYY");
            return dateFormat.format(dueDate);
        } else {
            return "Date non disponible";
        }
    }

}
