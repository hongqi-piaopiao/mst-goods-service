package com.thoughtworks.mstorderservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_item")
public class Item {
    @Id
    private String id;

    private String name;

    private double price;
}
