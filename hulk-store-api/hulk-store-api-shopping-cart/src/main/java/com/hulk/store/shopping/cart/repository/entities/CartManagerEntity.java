package com.hulk.store.shopping.cart.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cart_manager")
public class CartManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    Calendar createdDate;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @OneToMany(mappedBy = "cartManagerEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    List<CartItemEntity> cartItemEntities;

}
