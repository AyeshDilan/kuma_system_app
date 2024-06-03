package edu.iset.controller;

import edu.iset.dao.ItemEntity;
import edu.iset.dto.Item;
import edu.iset.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    //save items to database
    @PostMapping
    public void itemRegistration(@RequestBody Item item){
        itemService.registerItem(item);
    }

    //get all items from the table
    @GetMapping
    public List<Item> retriveAllItem(){
        return itemService.retriveAllItem();
    }

    //get item by name
    @GetMapping("/{itemName}")
    public Iterable<ItemEntity> retriveItemByName(@PathVariable String itemName){
        return itemService.findByItemName(itemName);
    }

    //update item stock
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStock(@PathVariable ("id")Long id, @RequestBody Item item) {
        itemService.updateItem(id,item);
        return ResponseEntity.ok().build();
    }

}
