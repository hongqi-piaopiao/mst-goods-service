package com.thoughtworks.mstorderservice.service;

import com.thoughtworks.mstorderservice.DTO.GoodDTO;
import com.thoughtworks.mstorderservice.Repository.GoodsRepository;
import com.thoughtworks.mstorderservice.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    private GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<GoodDTO> getAll() {
        List<Good> goods = goodsRepository.findAll();
        return goods.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public GoodDTO findGood(Long goodId) {
        Good good = goodsRepository.findOneById(goodId);
        return this.mapToDTO(good);
    }

    private GoodDTO mapToDTO(Good good) {
        return GoodDTO.builder()
                      .id(good.getId())
                      .name(good.getName())
                      .price(good.getPrice())
                      .build();
    }

    public GoodDTO order(String name, Long orderId) {
        Good good = goodsRepository.findOneByNameAndOrderIdIsNull(name);
        good.setOrderId(orderId);
        goodsRepository.saveAndFlush(good);
        return this.mapToDTO(good);
    }
}
