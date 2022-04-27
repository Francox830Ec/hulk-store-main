package com.hulk.store.security.controllers.implementations;

import com.hulk.store.security.controllers.contracts.IUserManagementController;
import com.hulk.store.security.model.common.UserInformation;
import com.hulk.store.security.model.request.UserCreateRequest;
import com.hulk.store.security.service.contracts.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
public class UserManagementController implements IUserManagementController {

    IUserService userService;

    @Autowired
    public UserManagementController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<ResponseEntity<Object>> create(UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest)
            .then(Mono.just(new ResponseEntity<>(HttpStatus.CREATED)))
            .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().build()))
            .doOnError(error -> log.error("Error getting inventory", error))
            .log();
    }

    @Override
    public Mono<ResponseEntity<UserInformation>> findByUsername(String username, String password) {
        return userService.getUser(username, password)
            .flatMap(userInformation -> Mono.just(ResponseEntity.ok().body(userInformation)))
            .onErrorResume(error -> Mono.just(ResponseEntity.notFound().build()))
            .doOnError(error -> log.error("Error getting inventory", error))
            .log();
    }

}
