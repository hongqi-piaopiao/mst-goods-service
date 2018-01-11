package com.thoughtworks.mstorderservice.apis;

import com.thoughtworks.mstorderservice.DTO.GoodDTO;
import com.thoughtworks.mstorderservice.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public List<GoodDTO> getList() {
        return goodsService.getAll();
    }

    @GetMapping(value = "/{goodId}")
    @ResponseStatus(HttpStatus.OK)
    public GoodDTO findGood(@PathVariable("goodId") Long goodId) {
        return goodsService.findGood(goodId);
    }

    @PostMapping(value = "/{name}/order/{orderId}")
    public GoodDTO order(@PathVariable("name") String name, @PathVariable("orderId") Long orderId) {
        return goodsService.order(name, orderId);
    }
}
