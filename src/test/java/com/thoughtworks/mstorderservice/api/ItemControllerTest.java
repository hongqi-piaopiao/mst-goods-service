package com.thoughtworks.mstorderservice.api;


import com.thoughtworks.mstorderservice.Repository.ItemRepository;
import com.thoughtworks.mstorderservice.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest extends BaseControllerTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void should_return_item_when_give_item_id() throws Exception {
        String item1Id = "1";
        String item2Id = "2";

        Item item = Item.builder().id(item1Id).name("iPhone8 64G").price(5888.8).build();
        Item item2 = Item.builder().id(item2Id).name("iPhone8 128G").price(7888.8).build();
        itemRepository.save(Arrays.asList(item, item2));

        mockMvc.perform(get("/api/items/{itemId}", item1Id))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("iPhone8 64G"))
               .andExpect(jsonPath("$.price").value(5888.8));
    }

}
