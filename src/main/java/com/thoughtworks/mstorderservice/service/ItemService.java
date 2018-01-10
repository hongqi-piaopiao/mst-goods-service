package com.thoughtworks.mstorderservice.service;

import com.thoughtworks.mstorderservice.entity.Item;

public interface ItemService {
    Item findItem(String id);
}
