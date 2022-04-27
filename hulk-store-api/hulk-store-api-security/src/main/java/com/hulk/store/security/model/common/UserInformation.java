package com.hulk.store.security.model.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInformation {

    @ApiModelProperty(value = "Username", required = true)
    @NotNull
    @NotEmpty
    String username;

    @ApiModelProperty(value = "Employee identification", required = true)
    @NotNull
    @NotEmpty
    String employeeId;
    
}
