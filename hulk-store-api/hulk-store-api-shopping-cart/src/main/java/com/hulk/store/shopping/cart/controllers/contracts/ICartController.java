package com.hulk.store.shopping.cart.controllers.contracts;

import com.hulk.store.shopping.cart.constants.CategoryRest;
import com.hulk.store.shopping.cart.model.common.Cart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "/cart", tags = {CategoryRest.CATEGORY_REST_SHOPPING_CART}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/cart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiImplicitParams({
    @ApiImplicitParam(name = "Content-Type", value = "Content type", paramType = "header", dataTypeClass = String.class, example = MediaType.APPLICATION_JSON_VALUE),
    @ApiImplicitParam(name = "accept", value = "Accept", paramType = "header", dataTypeClass = String.class, example = MediaType.APPLICATION_JSON_VALUE)
})
@Validated
public interface ICartController {

    @ApiOperation(value = "Register cart", notes = "Register new shopping cart")
    @PostMapping
    Mono<ResponseEntity<Object>> create(@RequestBody @Valid @NotNull Cart cart);

}
