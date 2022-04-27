package com.hulk.store.products.model.common;

import com.hulk.store.products.enums.Company;
import com.hulk.store.products.enums.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInformation {
    @ApiModelProperty(value = "Identification product", required = true)
    Long identification;

    @ApiModelProperty(value = "Product name", required = true)
    Product product;

    @ApiModelProperty(value = "Category name", required = true)
    Company company;

    @ApiModelProperty(value = "Stock product", required = true)
    BigInteger stock;
}
