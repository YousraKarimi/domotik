package episen.CustomerAccount.services;



import back.models.CustomerAccountModel.InvoiceDetails;
import episen.CustomerAccount.respositories.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;


    public InvoiceDetails addInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsRepository.save(invoiceDetails);
    }
    public List<InvoiceDetails> findAllInvoiceDetails(){
        return invoiceDetailsRepository.findAll();
    }
    public List<InvoiceDetails> getDetailsByDueDate(Date dueDate) {
        return invoiceDetailsRepository.findByDueDate(dueDate);
    }
    public InvoiceDetails findByIdInvoiceDetails(Long idInvoiceDetails) {
        Optional<InvoiceDetails> optionalInvoice = invoiceDetailsRepository.findById(idInvoiceDetails);
        return optionalInvoice.orElse(null);
    }
    public InvoiceDetails getDetailsByInvoiceId(Long invoiceId) {
        return invoiceDetailsRepository.findByInvoice_IdInvoice(invoiceId);
    }



}
