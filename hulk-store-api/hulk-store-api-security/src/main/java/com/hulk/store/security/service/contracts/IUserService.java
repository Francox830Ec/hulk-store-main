package com.hulk.store.security.service.contracts;

import com.hulk.store.security.model.common.UserInformation;
import com.hulk.store.security.model.request.UserCreateRequest;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<Void> createUser(UserCreateRequest userCreateRequest);

    Mono<UserInformation> getUser(String username, String password);

}
