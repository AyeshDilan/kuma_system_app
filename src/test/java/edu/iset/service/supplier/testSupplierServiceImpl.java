package edu.iset.service.supplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.SupplierEntity;
import edu.iset.dto.Supplier;
import edu.iset.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class testSupplierServiceImpl {
    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    private Supplier supplier;
    private SupplierEntity supplierEntity;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        supplier = Supplier.builder()
                .nameOfCompany("test")
                .location("test")
                .contactNumber("test")
                .email("test@gmail.com")
                .build();

        supplierEntity = new SupplierEntity();
        supplierEntity.setId(1L);
        supplierEntity.setNameOfCompany("test");
        supplierEntity.setLocation("test");
        supplierEntity.setContactNumber("test");
        supplierEntity.setEmail("test@gmail.com");
    }

    @Test
    void testRegisterSupplier(){
        when(objectMapper.convertValue(any(Supplier.class),eq(SupplierEntity.class)))
                .thenReturn(supplierEntity);

        supplierService.registerSupplier(supplier);

        verify(supplierRepository,times(1)).save(supplierEntity);
    }

    @Test
    void testRetriveAllSupplier(){
        List<SupplierEntity> supplierEntityList = new ArrayList<>();
        supplierEntityList.add(supplierEntity);

        when(supplierRepository.findAll()).thenReturn(supplierEntityList);
        when(objectMapper.convertValue(any(SupplierEntity.class),eq(Supplier.class)))
                .thenReturn(supplier);

        List<Supplier> result = supplierService.retriveAllSupplier();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("test", result.get(0).getNameOfCompany());

    }
}
