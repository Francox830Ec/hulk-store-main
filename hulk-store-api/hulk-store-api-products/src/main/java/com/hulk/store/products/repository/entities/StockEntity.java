package com.hulk.store.products.repository.entities;

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
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "product_id", nullable = false)
    Long productId;

    @Column(name = "quantity", nullable = false)
    BigInteger quantity;

}
