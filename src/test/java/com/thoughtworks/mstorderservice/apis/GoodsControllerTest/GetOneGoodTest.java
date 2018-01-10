package com.thoughtworks.mstorderservice.apis.GoodsControllerTest;


import com.thoughtworks.mstorderservice.MstOrderServiceApplicationTests;
import com.thoughtworks.mstorderservice.Repository.GoodsRepository;
import com.thoughtworks.mstorderservice.entity.Good;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetOneGoodTest extends MstOrderServiceApplicationTests {

    private GoodsRepository goodsRepository;

    @Autowired
    public GetOneGoodTest(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Test
    void should_return_good_when_give_good_id() throws Exception {
        Good item = Good.builder()
                        .name("iPhone8 64G")
                        .price(BigDecimal.valueOf(5888.8))
                        .build();
        Good good = goodsRepository.saveAndFlush(item);

        mockMvc.perform(get("/api/goods/{goodId}", good.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("iPhone8 64G"))
               .andExpect(jsonPath("$.price").value(5888.8));
    }

}
