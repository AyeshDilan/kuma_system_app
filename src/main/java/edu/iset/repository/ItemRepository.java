package edu.iset.repository;

import edu.iset.dao.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    <ItemEntity> Iterable<ItemEntity> findByItemName(String itemName);

}
