package edu.iset.service.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.ItemEntity;
import edu.iset.dto.Item;
import edu.iset.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ObjectMapper objectMapper;

    //save item in database
    @Override
    public void registerItem(Item item) {
        ItemEntity itemEntity = objectMapper.convertValue(item,ItemEntity.class);
        itemRepository.save(itemEntity);
    }

    //get all items from database
    @Override
    public List<Item> retriveAllItem() {
        List<Item> list = new ArrayList<>();
        Iterable<ItemEntity> itemEntityIterable = itemRepository.findAll();
        Iterator<ItemEntity> iterator = itemEntityIterable.iterator();
        if (iterator.hasNext()) {
            do {
                ItemEntity entity = iterator.next();
                Item item = objectMapper.convertValue(entity, Item.class);
                list.add(item);
            } while (iterator.hasNext());
        }
        return list;
    }

    //search item from database use item name
    @Override
    public Iterable<ItemEntity> findByItemName(String itemName) {
        return itemRepository.findByItemName(itemName);
    }

    //update stock
    @Override
    public void updateItem(Long id ,Item item) {
       ItemEntity existingItem = itemRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("User not found"));
       existingItem.setStock(item.getStock());
       itemRepository.save(existingItem);
    }
}
