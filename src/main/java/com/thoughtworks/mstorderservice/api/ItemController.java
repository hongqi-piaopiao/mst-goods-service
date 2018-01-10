package com.thoughtworks.mstorderservice.api;

import com.thoughtworks.mstorderservice.entity.Item;
import com.thoughtworks.mstorderservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Item list(@PathVariable("itemId") String itemId) {
        return itemService.findItem(itemId);
    }
}
