package com.thoughtworks.mstorderservice.apis.GoodsControllerTest;

import com.thoughtworks.mstorderservice.MstOrderServiceApplicationTests;
import com.thoughtworks.mstorderservice.entity.Good;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QueryGoodsTest extends MstOrderServiceApplicationTests {

    @Test
    public void should_return_goods_list() throws Exception {
        Good dev = Good.builder()
                       .name("dev")
                       .price(BigDecimal.valueOf(10.00))
                       .build();
        Good qa = Good.builder()
                      .name("qa")
                      .price(BigDecimal.valueOf(20.00))
                      .build();

        goodsRepository.save(Arrays.asList(dev, qa));
        goodsRepository.flush();


        mockMvc.perform(get("/api/goods")
                                .contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$.length()").value(2))
               .andExpect(jsonPath("$[0].name").value(dev.getName()))
               .andExpect(jsonPath("$[0].price").value(dev.getPrice()))
               .andExpect(jsonPath("$[1].name").value(qa.getName()))
               .andExpect(jsonPath("$[1].price").value(qa.getPrice()));
    }
}
