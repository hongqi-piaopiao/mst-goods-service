package com.thoughtworks.mstorderservice.Repository;

import com.thoughtworks.mstorderservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
    Item findById(String id);
}
