package edu.iset.repository;

import edu.iset.dao.SupplierEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity,Long> {
}
