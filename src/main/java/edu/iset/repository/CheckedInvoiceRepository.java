package edu.iset.repository;

import edu.iset.dao.CheckedInvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CheckedInvoiceRepository extends CrudRepository<CheckedInvoiceEntity,Long> {
}
