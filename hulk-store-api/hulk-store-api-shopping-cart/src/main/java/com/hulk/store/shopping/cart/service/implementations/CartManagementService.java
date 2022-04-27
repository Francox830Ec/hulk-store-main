package com.hulk.store.shopping.cart.service.implementations;

import com.hulk.store.shopping.cart.model.common.Cart;
import com.hulk.store.shopping.cart.repository.contracts.CartItemEntityRepository;
import com.hulk.store.shopping.cart.repository.contracts.CartManagerEntityRepository;
import com.hulk.store.shopping.cart.repository.entities.CartItemEntity;
import com.hulk.store.shopping.cart.repository.entities.CartManagerEntity;
import com.hulk.store.shopping.cart.service.contracts.ICartManagementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Calendar;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CartManagementService implements ICartManagementService {

    CartManagerEntityRepository cartManagerEntityRepository;
    CartItemEntityRepository cartItemEntityRepository;

    @Autowired
    public CartManagementService(CartManagerEntityRepository cartManagerEntityRepository,
                                 CartItemEntityRepository cartItemEntityRepository) {
        this.cartManagerEntityRepository = cartManagerEntityRepository;
        this.cartItemEntityRepository = cartItemEntityRepository;
    }

    @Override
    public Mono<Void> registerCart(Cart cart) {
        long cardId = new Random().nextInt();
        log.info("Registering cart with id: {}", cardId);
        return Mono.fromRunnable(() -> cartManagerEntityRepository.save(CartManagerEntity.builder()
                .createdDate(Calendar.getInstance())
                .userId(cart.getUserId())
                .cartItemEntities(cart.getCartItems().stream()
                    .map(cartItem -> CartItemEntity.builder()
                        .productId(cartItem.getProductId())
                        .quantity(cartItem.getQuantity())
                        .build())
                    .collect(Collectors.toList()))
                .build()))
            .subscribeOn(Schedulers.boundedElastic())
            .doOnError(e -> log.error("Error creating new user", e))
            .then();
    }

}
