package com.hulk.store.shopping.cart.model.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cart {

    @ApiModelProperty(value = "userId", required = true)
    @NotNull
    Long userId;

    @ApiModelProperty(value = "cartItems", required = true)
    @NotNull
    List<CartItem> cartItems;

}
