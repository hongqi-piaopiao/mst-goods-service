package com.thoughtworks.mstorderservice.service;

import com.thoughtworks.mstorderservice.entity.Good;

public interface GoodService {
    Good findGood(String id);
}
