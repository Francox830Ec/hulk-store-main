package com.hulk.store.products.controllers.implementations;

import com.hulk.store.products.controllers.contracts.IProductManagementController;
import com.hulk.store.products.model.common.ProductInformation;
import com.hulk.store.products.model.response.InventoryResponse;
import com.hulk.store.products.services.contracts.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RestController
@Log4j2
public class ProductManagementController implements IProductManagementController {

    IProductService productService;

    @Autowired
    public ProductManagementController(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public Mono<ResponseEntity<InventoryResponse>> inventory() {
        return productService.getInventory()
            .collectList()
            .flatMap(inventory -> Mono.just(ResponseEntity.ok().body(InventoryResponse.builder()
                    .products(inventory)
                    .build()))
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()))
                .doOnError(error -> log.error("Error getting inventory", error))
                .log());
    }

    @Override
    public Mono<ResponseEntity<ProductInformation>> sellProduct(Long productId, BigInteger quantity) {
        return productService.sellProduct(productId, quantity)
            .flatMap(productInformation -> Mono.just(ResponseEntity.ok().body(productInformation)))
            .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()))
            .doOnError(error -> log.error("Error getting inventory", error))
            .log();
    }

    @Override
    public Mono<ResponseEntity<ProductInformation>> buyProduct(Long productId, BigInteger quantity) {
        return productService.buyProduct(productId, quantity)
            .flatMap(productInformation -> Mono.just(ResponseEntity.ok().body(productInformation)))
            .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()))
            .doOnError(error -> log.error("Error getting inventory", error))
            .log();
    }

}
