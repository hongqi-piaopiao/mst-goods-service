package com.thoughtworks.mstorderservice.api;


import com.thoughtworks.mstorderservice.Repository.GoodRepository;
import com.thoughtworks.mstorderservice.entity.Good;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GoodControllerTest extends BaseControllerTest {
    @Autowired
    private GoodRepository goodRepository;

    @Test
    void should_return_item_when_give_item_id() throws Exception {
        String good1Id = "1";
        String good2Id = "2";

        Good item = Good.builder().id(good1Id).name("iPhone8 64G").price(5888.8).build();
        Good item2 = Good.builder().id(good2Id).name("iPhone8 128G").price(7888.8).build();
        goodRepository.save(Arrays.asList(item, item2));

        mockMvc.perform(get("/api/items/{itemId}", good1Id))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("iPhone8 64G"))
               .andExpect(jsonPath("$.price").value(5888.8));
    }

}
