package com.hulk.store.shopping.cart.controllers.implementations;

import com.hulk.store.shopping.cart.model.common.Cart;
import com.hulk.store.shopping.cart.model.common.CartItem;
import com.hulk.store.shopping.cart.repository.contracts.CartItemEntityRepository;
import com.hulk.store.shopping.cart.repository.contracts.CartManagerEntityRepository;
import com.hulk.store.shopping.cart.service.implementations.CartManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigInteger;
import java.util.List;

@WebFluxTest(controllers = CartController.class)
@Import(CartManagementService.class)
class CartControllerTest {

    @MockBean
    CartManagerEntityRepository cartManagerEntityRepositoryMock;

    @MockBean
    CartItemEntityRepository cartItemEntityRepositoryMock;

    @Autowired
    private WebTestClient webClient;

    @Test
    void givenNewUser_whenCreate_thenSuccess() {
        webClient.post()
            .uri("/cart")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(Cart.builder()
                .userId(100L)
                .cartItems(List.of(
                    CartItem.builder()
                        .productId(1L)
                        .quantity(BigInteger.ONE)
                        .build(),
                    CartItem.builder()
                        .productId(1L)
                        .quantity(BigInteger.TWO)
                        .build()
                ))
                .build()))
            .exchange()
            .expectStatus().isCreated();
    }

}
