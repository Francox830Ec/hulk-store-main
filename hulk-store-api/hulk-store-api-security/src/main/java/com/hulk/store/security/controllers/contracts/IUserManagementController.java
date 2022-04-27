package com.hulk.store.security.controllers.contracts;

import com.hulk.store.security.constants.CategoryRest;
import com.hulk.store.security.model.common.UserInformation;
import com.hulk.store.security.model.request.UserCreateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "/users", tags = {CategoryRest.CATEGORY_REST_SECURITY}, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiImplicitParams({
    @ApiImplicitParam(name = "Content-Type", value = "Content type", paramType = "header", dataTypeClass = String.class, example = MediaType.APPLICATION_JSON_VALUE),
    @ApiImplicitParam(name = "accept", value = "Accept", paramType = "header", dataTypeClass = String.class, example = MediaType.APPLICATION_JSON_VALUE)
})
@Validated
public interface IUserManagementController {

    @ApiOperation(value = "Register user", notes = "Register new user")
    @PostMapping(value = "/create")
    Mono<ResponseEntity<Object>> create(@RequestBody @Valid @NotNull UserCreateRequest userCreateRequest);

    @ApiOperation(value = "Find user", notes = "Find user")
    @GetMapping(value = "/find/{username}/{password}")
    Mono<ResponseEntity<UserInformation>> findByUsername(@PathVariable @Valid @NotNull String username, @PathVariable @Valid @NotNull String password);

}
