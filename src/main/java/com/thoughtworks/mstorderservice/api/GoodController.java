package com.thoughtworks.mstorderservice.api;

import com.thoughtworks.mstorderservice.entity.Good;
import com.thoughtworks.mstorderservice.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping(value = "/{goodId}")
    @ResponseStatus(HttpStatus.OK)
    public Good findGood(@PathVariable("goodId") String goodId) {
        return goodService.findGood(goodId);
    }
}
