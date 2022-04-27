package com.hulk.store.shopping.cart.service.contracts;

import com.hulk.store.shopping.cart.model.common.Cart;
import reactor.core.publisher.Mono;

public interface ICartManagementService {

    Mono<Void> registerCart(Cart cart);

}
