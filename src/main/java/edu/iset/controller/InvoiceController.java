package edu.iset.controller;

import edu.iset.dto.Invoice;
import edu.iset.dto.Item;
import edu.iset.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //save invoice for approval
    @PostMapping
    public void invoiceSave(@RequestBody Invoice invoice){
        invoiceService.saveInvoiceForApproval(invoice);
    }

    //get all invoices for approval
    @GetMapping
    public List<Invoice> retriveAllItem(){
        return invoiceService.retriveAllInvoice();
    }

    //get next invoice number
    @GetMapping("/next")
    public String getNextInvoiceNumber() {
        return invoiceService.getNextInvoiceNumber();
    }

    //remove checked invoice from the table using id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id){
        invoiceService.removedCheckedInvoice(id);
        return ResponseEntity.noContent().build();
    }

}
