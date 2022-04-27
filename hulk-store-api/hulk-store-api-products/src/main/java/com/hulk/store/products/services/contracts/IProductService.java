package com.hulk.store.products.services.contracts;

import com.hulk.store.products.model.common.ProductInformation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface IProductService {

    Flux<ProductInformation> getInventory();

    Mono<ProductInformation> sellProduct(Long productId, BigInteger quantity);

    Mono<ProductInformation> buyProduct(Long productId, BigInteger quantity);

}
