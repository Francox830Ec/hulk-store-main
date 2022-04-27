package com.hulk.store.products.repository.entities;

import com.hulk.store.products.enums.Company;
import com.hulk.store.products.enums.Product;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Enumerated(EnumType.STRING)
    Product product;

    @Enumerated(EnumType.STRING)
    Company company;
}
