package com.hulk.store.shopping.cart.repository.contracts;

import com.hulk.store.shopping.cart.repository.entities.CartManagerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartManagerEntityRepository extends CrudRepository<CartManagerEntity, Long> {
}
