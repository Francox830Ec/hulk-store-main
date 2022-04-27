package com.hulk.store.products.repository.contracts;

import com.hulk.store.products.repository.entities.StockEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Repository
@Transactional
public interface StockEntityRepository extends CrudRepository<StockEntity, Long> {

    Optional<StockEntity> findByProductId(Long productId);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE StockEntity  s SET s.quantity = :quantity WHERE s.productId = :productId")
    void updateStock(Long productId, BigInteger quantity);

}
