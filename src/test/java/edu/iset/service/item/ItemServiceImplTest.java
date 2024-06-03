package edu.iset.service.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.ItemEntity;
import edu.iset.dto.Item;
import edu.iset.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<ItemEntity> argumentCaptor;
    @InjectMocks
    private ItemServiceImpl itemService;

    private Item item;
    private ItemEntity itemEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        item = Item.builder()
                .id(1L)
                .itemName("test")
                .itemCode("test")
                .category("test")
                .supplierName("test")
                .unit("test")
                .stock(1)
                .cost(1.0)
                .build();

        itemEntity = new ItemEntity();
        itemEntity.setId(1L);
        itemEntity.setItemName("test");
        itemEntity.setItemCode("test");
        itemEntity.setCategory("test");
        itemEntity.setSupplierName("test");
        itemEntity.setUnit("test");
        itemEntity.setStock(1);
        itemEntity.setCost(1.0);
    }

    @Test
    void testRegisterItem() {
        when(objectMapper.convertValue(any(Item.class),eq(ItemEntity.class)))
                .thenReturn(itemEntity);
        itemService.registerItem(item);
        verify(itemRepository,times(1)).save(itemEntity);
    }

    @Test
    void testRetrieveAllItem() {
        List<ItemEntity> entityList = new ArrayList<>();
        entityList.add(itemEntity);

        when(itemRepository.findAll()).thenReturn(entityList);
        when(objectMapper.convertValue(any(ItemEntity.class), eq(Item.class)))
                .thenReturn(item);

        List<Item> itemList = itemService.retriveAllItem();

        assertEquals(1, itemList.size());
        assertEquals("test", itemList.get(0).getItemName());
    }

    @Test
    void testFindByItemName() {
        String itemName = "test";
        ItemEntity itemEntity1 = new ItemEntity();
        itemEntity1.setItemName(itemName);
        when(itemRepository.findByItemName(itemName)).thenReturn(Collections.singletonList(itemEntity1));

        Iterable<ItemEntity> result = itemService.findByItemName(itemName);

        assertNotNull(result);
        assertEquals(itemName,result.iterator().next().getItemName());
        verify(itemRepository,times(1)).findByItemName(itemName);
    }

    @Test
    void testUpdateItem(){
        Long id = 1L;
        Item itemToUpdate = new Item();
        itemToUpdate.setStock(10);

        ItemEntity existingItem = new ItemEntity();
        existingItem.setId(id);
        existingItem.setStock(5);

        when(itemRepository.findById(id)).thenReturn(Optional.of(existingItem));

        itemService.updateItem(id,itemToUpdate);

        assertEquals(itemToUpdate.getStock(),existingItem.getStock());
        verify(itemRepository,times(1)).findById(id);
        verify(itemRepository,times(1)).save(existingItem);
    }

    @Test
    void testUpdateItemNotFound(){
        Long id = 1L;
        Item itemToUpdate = new Item();

        when(itemRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->itemService.updateItem(id,itemToUpdate));
        verify(itemRepository, times(1)).findById(id);
        verify(itemRepository,never()).save(any());
    }

}
