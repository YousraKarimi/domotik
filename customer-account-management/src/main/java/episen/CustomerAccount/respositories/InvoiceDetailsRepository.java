package episen.CustomerAccount.respositories;


import back.models.CustomerAccountModel.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Long> {

//    @Query("SELECT id FROM InvoiceDetails id WHERE id.dueDate = :dueDate")
//    List<InvoiceDetails> findByDueDate(@Param("dueDate") Date dueDate);
@Query("SELECT id FROM InvoiceDetails id WHERE DATE(id.dueDate) = DATE(:dueDate)")
List<InvoiceDetails> findByDueDate(@Param("dueDate") Date dueDate);

    @Query("SELECT id FROM InvoiceDetails id WHERE id.invoice.idInvoice = :invoiceId")
    InvoiceDetails findByInvoice_IdInvoice(@Param("invoiceId") Long invoiceId);

    @Query("SELECT id FROM InvoiceDetails id WHERE id.invoice.idInvoice = :invoiceId")
    Optional<InvoiceDetails> findByInvoiceId(@Param("invoiceId") Long invoiceId);


}
