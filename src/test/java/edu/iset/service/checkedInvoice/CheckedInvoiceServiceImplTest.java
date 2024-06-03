package edu.iset.service.checkedInvoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.CheckedInvoiceEntity;
import edu.iset.dto.CheckedInvoice;
import edu.iset.repository.CheckedInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckedInvoiceServiceImplTest {

    @Mock
    private CheckedInvoiceRepository checkedInvoiceRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CheckedInvoiceServiceImpl checkedInvoiceService;

    private CheckedInvoice checkedInvoice;
    private CheckedInvoiceEntity checkedInvoiceEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        checkedInvoice = CheckedInvoice.builder()
                .checkedInvoiceId(1L)
                .invoiceNumber("000001")
                .invoicedDate("01-04-2024")
                .itemName("2x2 sheet")
                .units("Nos")
                .quantity(5.0)
                .cost(50.0)
                .margin(6.0)
                .wholesalePrice(150.0)
                .subTotal(170.0)
                .salesRef("Mahesh")
                .customer("Dasa")
                .paymentType("cash")
                .remarks("cash")
                .lorryNumber("LA-2532")
                .taxTotal(0.0)
                .discount(1.0)
                .netTotal(168.0)
                .approvedBy("Maleesha")
                .approvedDate("08-05-2024")
                .status(true)
                .build();

        checkedInvoiceEntity = new CheckedInvoiceEntity();
        checkedInvoiceEntity.setCheckedInvoiceId(1L);
        checkedInvoiceEntity.setInvoiceNumber("000001");
        checkedInvoiceEntity.setInvoicedDate("01-04-2024");
        checkedInvoiceEntity.setItemName("2x2 sheet");
        checkedInvoiceEntity.setUnits("Nos");
        checkedInvoiceEntity.setQuantity(5.0);
        checkedInvoiceEntity.setCost(50.0);
        checkedInvoiceEntity.setMargin(6.0);
        checkedInvoiceEntity.setWholesalePrice(150.0);
        checkedInvoiceEntity.setSubTotal(170.0);
        checkedInvoiceEntity.setSalesRef("Mahesh");
        checkedInvoiceEntity.setCustomer("Dasa");
        checkedInvoiceEntity.setPaymentType("cash");
        checkedInvoiceEntity.setRemarks("cash");
        checkedInvoiceEntity.setLorryNumber("LA-2532");
        checkedInvoiceEntity.setTaxTotal(0.0);
        checkedInvoiceEntity.setDiscount(1.0);
        checkedInvoiceEntity.setNetTotal(168.0);
        checkedInvoiceEntity.setApprovedBy("Maleesha");
        checkedInvoiceEntity.setApprovedDate("08-05-2024");
        checkedInvoiceEntity.setStatus(true);
    }

    @Test
    void savecheckedInvoice() {
        when(objectMapper.convertValue(any(CheckedInvoice.class), eq(CheckedInvoiceEntity.class)))
                .thenReturn(checkedInvoiceEntity);

        checkedInvoiceService.saveCheckedInvoice(checkedInvoice);

        verify(checkedInvoiceRepository, times(1)).save(checkedInvoiceEntity);
    }

    @Test
    void retrieveAllCheckedInvoices() {
        List<CheckedInvoiceEntity> entityList = new ArrayList<>();
        entityList.add(checkedInvoiceEntity);

        when(checkedInvoiceRepository.findAll()).thenReturn(entityList);
        when(objectMapper.convertValue(any(CheckedInvoiceEntity.class), eq(CheckedInvoice.class)))
                .thenReturn(checkedInvoice);

        List<CheckedInvoice> result = checkedInvoiceService.retriveAllCheckedInvoices();

        assertEquals(1, result.size());
        assertEquals("000001", result.get(0).getInvoiceNumber());
    }
}
