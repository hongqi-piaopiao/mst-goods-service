package com.thoughtworks.mstorderservice.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GoodDTO {
    private Long id;
    private String name;
    private BigDecimal price;
}
