package com.hulk.store.shopping.cart.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "product_id", nullable = false)
    Long productId;

    @Column(name = "quantity", nullable = false)
    BigInteger quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_manager_id")
    CartManagerEntity cartManagerEntity;

}
