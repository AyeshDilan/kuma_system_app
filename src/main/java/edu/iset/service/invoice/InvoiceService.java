package edu.iset.service.invoice;

import edu.iset.dto.Invoice;

import java.util.List;

public interface InvoiceService {
void saveInvoiceForApproval(Invoice invoice);

    List<Invoice> retriveAllInvoice();

    String getNextInvoiceNumber();

    void removedCheckedInvoice(Long id);
}

