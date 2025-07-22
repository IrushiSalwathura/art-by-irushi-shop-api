package com.personal.artbyirushishopapi.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    //ManyToOne already added in the OrderItem, so no need to add this
//    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
//    private Set<OrderItem> orderItems = new HashSet<>();
}
