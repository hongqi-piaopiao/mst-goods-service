package com.thoughtworks.mstorderservice.service.impl;

import com.thoughtworks.mstorderservice.Repository.ItemRepository;
import com.thoughtworks.mstorderservice.entity.Item;
import com.thoughtworks.mstorderservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item findItem(String id) {
        return itemRepository.findById(id);
    }
}
