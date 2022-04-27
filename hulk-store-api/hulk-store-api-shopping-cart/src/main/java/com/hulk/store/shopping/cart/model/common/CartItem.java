package com.hulk.store.shopping.cart.model.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartItem {

    @ApiModelProperty(value = "productId", required = true)
    @NotNull
    @NotEmpty
    Long productId;

    @ApiModelProperty(value = "quantity", required = true)
    @NotNull
    @NotEmpty
    BigInteger quantity;

}
