package com.hulk.store.products.services.implementations;

import com.hulk.store.products.model.common.ProductInformation;
import com.hulk.store.products.repository.contracts.ProductEntityRepository;
import com.hulk.store.products.repository.contracts.StockEntityRepository;
import com.hulk.store.products.repository.entities.ProductEntity;
import com.hulk.store.products.repository.entities.StockEntity;
import com.hulk.store.products.services.contracts.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigInteger;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductService implements IProductService {

    ProductEntityRepository productEntityRepository;
    StockEntityRepository stockEntityRepository;

    @Autowired
    public ProductService(ProductEntityRepository productEntityRepository,
                          StockEntityRepository stockEntityRepository) {
        this.productEntityRepository = productEntityRepository;
        this.stockEntityRepository = stockEntityRepository;
    }

    @Override
    public Flux<ProductInformation> getInventory() {
        return Flux.fromIterable(productEntityRepository.findAll()
                .stream()
                .map(this::transformToProductInformation)
                .collect(Collectors.toList()))
            .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ProductInformation> sellProduct(Long productId, BigInteger quantity) {
        return Mono.fromCallable(() -> stockEntityRepository.findByProductId(productId).orElseThrow(() -> new Exception("Stock Product not found")))
            .subscribeOn(Schedulers.boundedElastic())
            .switchIfEmpty(Mono.error(new Exception("Product not found")))
            .flatMap(stockEntity -> {
                if (stockEntity.getQuantity().compareTo(quantity) < 0) {
                    return Mono.error(new Exception("Not enough stock"));
                }
                return Mono.just(stockEntity);
            })
            .flatMap(stockEntity -> Mono.fromCallable(() -> {
                    stockEntityRepository.updateStock(productId, stockEntity.getQuantity().subtract(quantity));
                    return transformToProductInformation(productEntityRepository.findById(productId).orElseThrow(() -> new Exception("Product not found")));
                })
                .subscribeOn(Schedulers.boundedElastic()))
            .doOnError(e -> log.error("Error while selling product", e));
    }

    @Override
    public Mono<ProductInformation> buyProduct(Long productId, BigInteger quantity) {
        return Mono.fromCallable(() -> stockEntityRepository.findByProductId(productId).orElseThrow(() -> new Exception("Stock Product not found")))
            .subscribeOn(Schedulers.boundedElastic())
            .switchIfEmpty(Mono.error(new Exception("Product not found")))
            .flatMap(stockEntity -> Mono.fromCallable(() -> {
                    stockEntityRepository.updateStock(productId, stockEntity.getQuantity().add(quantity));
                    return transformToProductInformation(productEntityRepository.findById(productId).orElseThrow(() -> new Exception("Product not found")));
                })
                .subscribeOn(Schedulers.boundedElastic()))
            .doOnError(e -> log.error("Error while buying product", e));
    }

    private ProductInformation transformToProductInformation(ProductEntity productEntity) {
        return ProductInformation.builder()
            .product(productEntity.getProduct())
            .company(productEntity.getCompany())
            .identification(productEntity.getId())
            .stock(stockEntityRepository.findByProductId(productEntity.getId()).orElse(StockEntity.builder().quantity(BigInteger.ZERO).build()).getQuantity())
            .build();
    }

}
