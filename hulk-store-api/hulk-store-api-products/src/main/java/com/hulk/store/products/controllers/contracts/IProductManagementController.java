package com.hulk.store.products.controllers.contracts;

import com.hulk.store.products.constants.CategoryRest;
import com.hulk.store.products.model.common.ProductInformation;
import com.hulk.store.products.model.response.InventoryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Api(value = "/management", tags = {CategoryRest.CATEGORY_REST_MANAGEMENT}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/management", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiImplicitParams({
    @ApiImplicitParam(name = "Content-type", value = "Content type", paramType = "header", dataTypeClass = String.class, example = MediaType.APPLICATION_JSON_VALUE),
    @ApiImplicitParam(name = "Accept", value = "Accept", paramType = "header", dataTypeClass = String.class, example = MediaType.APPLICATION_JSON_VALUE)
})
@Validated
public interface IProductManagementController {

    @ApiOperation(value = "Get inventory products", notes = "Get current inventory of products")
    @GetMapping(value = "/inventory")
    Mono<ResponseEntity<InventoryResponse>> inventory();

    @ApiOperation(value = "Sell product", notes = "Sell product by product id")
    @PutMapping(value = "/sell/{productId}/quantity/{quantity}")
    Mono<ResponseEntity<ProductInformation>> sellProduct(@PathVariable("productId") @Valid @NotNull Long productId, @PathVariable("quantity") BigInteger quantity);

    @ApiOperation(value = "Buy product", notes = "Buy product by product id")
    @PutMapping(value = "/buy/{productId}/quantity/{quantity}")
    Mono<ResponseEntity<ProductInformation>> buyProduct(@PathVariable("productId") @Valid @NotNull Long productId, @PathVariable("quantity") BigInteger quantity);

}
