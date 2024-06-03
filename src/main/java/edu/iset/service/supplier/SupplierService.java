package edu.iset.service.supplier;

import edu.iset.dto.Supplier;

import java.util.List;

public interface SupplierService {
    void registerSupplier(Supplier supplier);

    List<Supplier> retriveAllSupplier();
}
