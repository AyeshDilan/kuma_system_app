package edu.iset.service.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.InvoiceEntity;
import edu.iset.dto.Invoice;
import edu.iset.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
@Autowired
private InvoiceRepository invoiceRepository;

@Autowired
private ObjectMapper objectMapper;

    //Save invoice for Approval
    @Override
    public void saveInvoiceForApproval(Invoice invoice) {
        InvoiceEntity invoiceEntity = objectMapper.convertValue(invoice, InvoiceEntity.class);
        invoiceRepository.save(invoiceEntity);
    }

    //get all invoice for approval
    @Override
    public List<Invoice> retriveAllInvoice() {
        List<Invoice> list = new ArrayList<>();
        Iterable<InvoiceEntity> invoiceEntityIterable = invoiceRepository.findAll();
        Iterator<InvoiceEntity> iterator = invoiceEntityIterable.iterator();
        if (iterator.hasNext()){
            do {
                InvoiceEntity entity = iterator.next();
                Invoice invoice = objectMapper.convertValue(entity, Invoice.class);
                list.add(invoice);
            }while (iterator.hasNext());
        }
        return list;
    }

    //get next invoice number
    @Override
    public String getNextInvoiceNumber() {
        Integer latestInvoiceNumber = invoiceRepository.findMaxInvoiceNumber();
        int nextNumericPart = (latestInvoiceNumber != null) ? latestInvoiceNumber + 1 : 1;
        return String.format("%06d", nextNumericPart);
    }

    //remove checked invoice from the database table
    @Override
    public void removedCheckedInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }


}
