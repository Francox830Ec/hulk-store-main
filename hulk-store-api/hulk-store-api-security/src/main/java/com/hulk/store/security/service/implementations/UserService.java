package com.hulk.store.security.service.implementations;

import com.hulk.store.security.model.common.UserInformation;
import com.hulk.store.security.model.request.UserCreateRequest;
import com.hulk.store.security.repository.contracts.UserEntityRepository;
import com.hulk.store.security.repository.entities.UserEntity;
import com.hulk.store.security.service.contracts.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Log4j2
public class UserService implements IUserService {

    UserEntityRepository userEntityRepository;

    @Autowired
    public UserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public Mono<Void> createUser(UserCreateRequest userCreateRequest) {
        return Mono.fromCallable(() -> userEntityRepository.findByEmployeeId(userCreateRequest.getEmployeeId()))
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(userEntity -> userEntity.isEmpty() ? Mono.just(userCreateRequest) : Mono.error(new Exception("User already exists")))
            .flatMap(o -> Mono.fromCallable(() -> userEntityRepository.save(UserEntity.builder()
                    .employeeId(userCreateRequest.getEmployeeId())
                    .password(userCreateRequest.getPassword())
                    .username(userCreateRequest.getUsername())
                    .build()))
                .subscribeOn(Schedulers.boundedElastic())
            )
            .doOnError(e -> log.error("Error creating new user", e))
            .then();
    }

    @Override
    public Mono<UserInformation> getUser(String username, String password) {
        return Mono.fromCallable(() -> userEntityRepository.findByUsernameAndPassword(username, password).orElseThrow())
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(userEntity -> Mono.fromCallable(() -> UserInformation.builder()
                .username(userEntity.getUsername())
                .employeeId(userEntity.getEmployeeId())
                .build()))
            .doOnError(e -> log.error("Error get user", e));
    }

}
