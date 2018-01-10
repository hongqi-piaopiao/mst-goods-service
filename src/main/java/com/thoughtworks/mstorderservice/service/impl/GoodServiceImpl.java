package com.thoughtworks.mstorderservice.service.impl;

import com.thoughtworks.mstorderservice.Repository.GoodRepository;
import com.thoughtworks.mstorderservice.entity.Good;
import com.thoughtworks.mstorderservice.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Override
    public Good findGood(String id) {
        return goodRepository.findById(id);
    }
}
