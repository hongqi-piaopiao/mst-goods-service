package com.thoughtworks.mstorderservice.service;

import com.thoughtworks.mstorderservice.DTO.GoodDTO;
import com.thoughtworks.mstorderservice.Repository.GoodsRepository;
import com.thoughtworks.mstorderservice.entity.Good;
import com.thoughtworks.mstorderservice.exception.ErrorCode;
import com.thoughtworks.mstorderservice.exception.MstException;
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
        List<Good> goods = goodsRepository.findAllByOrderIdIsNull();
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
                      .orderId(good.getOrderId())
                      .build();
    }

    public GoodDTO order(Long goodId, Long orderId) {
        Good good = goodsRepository.findOneById(goodId);
        if (good.getOrderId() != null) {
            throw new MstException(ErrorCode.GoodOrdered);
        }
        good.setOrderId(orderId);
        Good orderedGood = goodsRepository.saveAndFlush(good);
        return this.mapToDTO(orderedGood);
    }
}
