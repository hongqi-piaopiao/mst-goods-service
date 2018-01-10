package com.thoughtworks.mstorderservice.Repository;

import com.thoughtworks.mstorderservice.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, String> {
    Good findById(String id);
}
