package edu.iset.controller;

import edu.iset.dto.Supplier;
import edu.iset.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    //save supplier
    @PostMapping
    public void supplierRegistration(@RequestBody Supplier supplier){
        supplierService.registerSupplier(supplier);
    }

    //get all suppliers
    @GetMapping
    List<Supplier> retriveAllSupplier(){
        return supplierService.retriveAllSupplier();
    }
}
