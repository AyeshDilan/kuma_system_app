package edu.iset.repository;

import edu.iset.dao.InvoiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity,Long> {
    @Query("SELECT MAX(i.invoiceNumber) FROM InvoiceEntity i")
    Integer findMaxInvoiceNumber();
}
