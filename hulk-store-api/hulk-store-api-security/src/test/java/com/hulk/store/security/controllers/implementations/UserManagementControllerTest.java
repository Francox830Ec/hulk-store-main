package com.hulk.store.security.controllers.implementations;

import com.hulk.store.security.model.common.UserInformation;
import com.hulk.store.security.model.request.UserCreateRequest;
import com.hulk.store.security.repository.contracts.UserEntityRepository;
import com.hulk.store.security.repository.entities.UserEntity;
import com.hulk.store.security.service.implementations.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Optional;

@WebFluxTest(controllers = UserManagementController.class)
@Import(UserService.class)
class UserManagementControllerTest {

    @MockBean
    UserEntityRepository userEntityRepositoryMock;

    @Autowired
    private WebTestClient webClient;

    @Test
    void givenNewUser_whenCreate_thenSuccess() {
        webClient.post()
            .uri("/users/create")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(UserCreateRequest.builder()
                .employeeId("001")
                .username("fakeUser")
                .password("fakePassword")
                .build()))
            .exchange()
            .expectStatus().isCreated();
    }

    @Test
    void givenDuplicateUser_whenCreate_thenFail() {
        Mockito.when(userEntityRepositoryMock.findByEmployeeId("001")).thenReturn(Optional.of(UserEntity.builder().build()));

        webClient.post()
            .uri("/users/create")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(UserCreateRequest.builder()
                .employeeId("001")
                .username("fakeUser")
                .password("fakePassword")
                .build()))
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    void givenExistsUser_whenGetUser_thenSuccess() {
        Mockito.when(userEntityRepositoryMock.findByUsernameAndPassword("jhon", "abc123")).thenReturn(Optional.of(UserEntity.builder().build()));

        webClient.get()
            .uri("/users/find/jhon/abc123")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(UserInformation.class);
    }

    @Test
    void givenNotExistsUser_whenGetUser_thenFail() {
        Mockito.when(userEntityRepositoryMock.findByUsernameAndPassword("jhon", "abc123")).thenReturn(Optional.empty());

        webClient.get()
            .uri("/users/find/jhon/abc123")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }

}
