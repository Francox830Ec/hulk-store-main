package com.hulk.store.shopping.cart.controllers.implementations;

import com.hulk.store.shopping.cart.controllers.contracts.ICartController;
import com.hulk.store.shopping.cart.model.common.Cart;
import com.hulk.store.shopping.cart.service.contracts.ICartManagementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
public class CartController implements ICartController {

    ICartManagementService cartManagementService;

    @Autowired
    public CartController(ICartManagementService cartManagementService) {
        this.cartManagementService = cartManagementService;
    }

    @Override
    public Mono<ResponseEntity<Object>> create(Cart cart) {
        return cartManagementService.registerCart(cart)
            .then(Mono.just(new ResponseEntity<>(HttpStatus.CREATED)))
            .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()))
            .doOnError(error -> log.error("Error register cart", error))
            .log();
    }

}
