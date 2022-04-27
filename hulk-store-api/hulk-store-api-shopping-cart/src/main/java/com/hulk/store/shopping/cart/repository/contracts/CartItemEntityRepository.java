package com.hulk.store.shopping.cart.repository.contracts;

import com.hulk.store.shopping.cart.repository.entities.CartItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemEntityRepository extends CrudRepository<CartItemEntity, Long> {
}
