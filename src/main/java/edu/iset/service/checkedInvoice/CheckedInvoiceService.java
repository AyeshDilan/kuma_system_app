package edu.iset.service.checkedInvoice;

import edu.iset.dto.CheckedInvoice;

import java.util.List;

public interface CheckedInvoiceService {
    void saveCheckedInvoice(CheckedInvoice checkedInvoice);

    List<CheckedInvoice> retriveAllCheckedInvoices();
}
