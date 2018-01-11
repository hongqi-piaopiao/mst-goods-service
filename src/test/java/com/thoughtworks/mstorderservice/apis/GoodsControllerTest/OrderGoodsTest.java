package com.thoughtworks.mstorderservice.apis.GoodsControllerTest;

import com.thoughtworks.mstorderservice.MstOrderServiceApplicationTests;
import com.thoughtworks.mstorderservice.entity.Good;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderGoodsTest extends MstOrderServiceApplicationTests {

    @Test
    public void should_return_goods_dto_and_add_the_orderId_to_the_good() throws Exception {
        Good dev = Good.builder().name("dev").price(BigDecimal.valueOf(10.00)).build();
        Good qa = Good.builder().name("qa").price(BigDecimal.valueOf(20.00)).build();

        goodsRepository.save(Arrays.asList(dev, qa));
        goodsRepository.flush();

        Long orderId = 666L;


        mockMvc.perform(post("/api/goods/{goodName}/order/{orderId}", qa.getName(), orderId)
                                .contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(qa.getId()))
               .andExpect(jsonPath("$.name").value(qa.getName()))
               .andExpect(jsonPath("$.price").value(qa.getPrice()));

        Good orderedQa = goodsRepository.findOneById(qa.getId());

        assertEquals(orderedQa.getOrderId(), orderId);
    }
}
