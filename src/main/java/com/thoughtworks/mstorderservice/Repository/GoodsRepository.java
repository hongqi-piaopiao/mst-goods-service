package com.thoughtworks.mstorderservice.Repository;

import com.thoughtworks.mstorderservice.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Good, Long> {

    List<Good> findAll();

    List<Good> findAllByOrderIdIsNull();

    Good findOneById(Long id);
}
