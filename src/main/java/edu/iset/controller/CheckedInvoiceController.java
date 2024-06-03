package edu.iset.controller;

import edu.iset.dto.CheckedInvoice;
import edu.iset.service.checkedInvoice.CheckedInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/checkedInvoice")
public class CheckedInvoiceController {
    @Autowired
    CheckedInvoiceService checkedInvoiceService;

    //save checked invoice
    @PostMapping
    public void saveCheckedInvoice(@RequestBody CheckedInvoice checkedInvoice){
        checkedInvoiceService.saveCheckedInvoice(checkedInvoice);
    }

    //get all checked invoice
    @GetMapping
    public List<CheckedInvoice> retriveCheckedInvoice(){
        return checkedInvoiceService.retriveAllCheckedInvoices();
    }
}
