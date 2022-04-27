package com.hulk.store.products.repository.contracts;

import com.hulk.store.products.repository.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEntityRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();

}
