package com.thoughtworks.mstorderservice.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;
}
