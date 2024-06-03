package edu.iset.service.supplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.SupplierEntity;
import edu.iset.dto.Supplier;
import edu.iset.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ObjectMapper mapper;

    //save registered supplier
    @Override
    public void registerSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = mapper.convertValue(supplier, SupplierEntity.class);
        supplierRepository.save(supplierEntity);
    }

    //get all suppliers
    @Override
    public List<Supplier> retriveAllSupplier() {
        List<Supplier> list = new ArrayList<>();
        Iterable<SupplierEntity> supplierEntityIterable = supplierRepository.findAll();
        Iterator<SupplierEntity> iterator = supplierEntityIterable.iterator();
        if (iterator.hasNext()) {
            do {
                SupplierEntity entity = iterator.next();
                Supplier supplier = mapper.convertValue(entity, Supplier.class);
                list.add(supplier);
            } while (iterator.hasNext());
        }
        return list;
    }

}
