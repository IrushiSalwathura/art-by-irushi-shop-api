package com.personal.artbyirushishopapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit_price")
    private BigDecimal price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();
}
