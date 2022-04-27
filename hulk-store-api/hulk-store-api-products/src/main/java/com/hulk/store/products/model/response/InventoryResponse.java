package com.hulk.store.products.model.response;

import com.hulk.store.products.model.common.ProductInformation;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    @ApiModelProperty(value = "List of products", required = true)
    List<ProductInformation> products;
}
