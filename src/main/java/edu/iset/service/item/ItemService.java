package edu.iset.service.item;

import edu.iset.dao.ItemEntity;
import edu.iset.dto.Item;

import java.util.List;

public interface ItemService {
    void registerItem(Item item);

    List<Item> retriveAllItem();

    public Iterable<ItemEntity> findByItemName(String itemName);

    void updateItem(Long id , Item item);


}
