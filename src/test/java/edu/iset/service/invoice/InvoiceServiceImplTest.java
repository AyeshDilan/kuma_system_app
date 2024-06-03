package edu.iset.service.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.InvoiceEntity;
import edu.iset.dto.Invoice;
import edu.iset.repository.InvoiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private Invoice invoice;
    private InvoiceEntity invoiceEntity;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        invoice = Invoice.builder()
                .invoiceForApprovalId(1L)
                .itemName("test")
                .units("test")
                .invoiceNumber("test")
                .cost(1.0)
                .margin(1.0)
                .sellingPrice(1.0)
                .quantity(1.0)
                .inStock(1.0)
                .itemsValue(1.0)
                .currentDate("test")
                .selectedRef("test")
                .selectedCustomer("test")
                .paymentType("test")
                .selectedRemarksType("test")
                .selectedAssignTo("test")
                .selectedAssignName("test")
                .lorryNumber("test")
                .taxTotal(1.0)
                .discount(1.0)
                .netTotalOfInvoice(1.0)
                .build();

        invoiceEntity = new InvoiceEntity();
        invoiceEntity.setInvoiceForApprovalId(1L);
        invoiceEntity.setItemName("test");
        invoiceEntity.setUnits("test");
        invoiceEntity.setInvoiceNumber("test");
        invoiceEntity.setCost(1.0);
        invoiceEntity.setMargin(1.0);
        invoiceEntity.setSellingPrice(1.0);
        invoiceEntity.setQuantity(1.0);
        invoiceEntity.setInStock(1.0);
        invoiceEntity.setItemsValue(1.0);
        invoiceEntity.setCurrentDate("test");
        invoiceEntity.setSelectedRef("test");
        invoiceEntity.setSelectedCustomer("test");
        invoiceEntity.setPaymentType("test");
        invoiceEntity.setSelectedRemarksType("test");
        invoiceEntity.setSelectedAssignTo("test");
        invoiceEntity.setSelectedAssignName("test");
        invoiceEntity.setLorryNumber("test");
        invoiceEntity.setTaxTotal(1.0);
        invoiceEntity.setDiscount(1.0);
        invoiceEntity.setNetTotalOfInvoice(1.0);
    }

    @Test
    void saveInvoiceForApprovalTest(){
        when(objectMapper.convertValue(any(Invoice.class),eq(InvoiceEntity.class)))
                .thenReturn(invoiceEntity);

        invoiceService.saveInvoiceForApproval(invoice);

        verify(invoiceRepository, times(1)).save(invoiceEntity);
    }

    @Test
    void retrieveAllCheckedInvoicesTest(){
        List<InvoiceEntity> entityList = new ArrayList<>();
        entityList.add(invoiceEntity);

        when(invoiceRepository.findAll()).thenReturn(entityList);
        when(objectMapper.convertValue(any(InvoiceEntity.class),eq(Invoice.class)))
                .thenReturn(invoice);

        List<Invoice> result = invoiceService.retriveAllInvoice();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("test", result.get(0).getItemName());
    }

    @Test
    void testGetNextInvoiceNumber_WhenNoPreviousInvoices(){
        when(invoiceRepository.findMaxInvoiceNumber()).thenReturn(null);
        String nextInvoiceNumber = invoiceService.getNextInvoiceNumber();
        Assertions.assertEquals("000001", nextInvoiceNumber);
    }

    @Test
    void testGetNextInvoiceNumber_WhenPreviousInvoicesExist(){
        when(invoiceRepository.findMaxInvoiceNumber()).thenReturn(125);
        String nextInvoiceNumber = invoiceService.getNextInvoiceNumber();
        Assertions.assertEquals("000126", nextInvoiceNumber);
    }

    @Test
    void removedCheckedInvoiceTest(){
        Long invoiceId = 1L;
        invoiceService.removedCheckedInvoice(invoiceId);
        verify(invoiceRepository, times(1)).deleteById(invoiceId);
    }
}
