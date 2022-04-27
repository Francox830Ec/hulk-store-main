package com.hulk.store.products.controllers.implementations;

import com.hulk.store.products.enums.Company;
import com.hulk.store.products.enums.Product;
import com.hulk.store.products.model.common.ProductInformation;
import com.hulk.store.products.model.response.InventoryResponse;
import com.hulk.store.products.repository.contracts.ProductEntityRepository;
import com.hulk.store.products.repository.contracts.StockEntityRepository;
import com.hulk.store.products.repository.entities.ProductEntity;
import com.hulk.store.products.repository.entities.StockEntity;
import com.hulk.store.products.services.implementations.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebFluxTest(controllers = ProductManagementController.class)
@Import(ProductService.class)
class ProductManagementControllerTest {

    @MockBean
    ProductEntityRepository productEntityRepositoryMock;

    @MockBean
    StockEntityRepository stockEntityRepositoryMock;

    @Autowired
    private WebTestClient webClient;

    @Test
    void givenExistData_whenInventory_thenSuccess() {
        Mockito.when(productEntityRepositoryMock.findAll()).thenReturn(List.of(
            ProductEntity.builder().id(100L).product(Product.ACCESSORIES).company(Company.DC).build(),
            ProductEntity.builder().id(101L).product(Product.COMICS).company(Company.MARVEL).build()
        ));

        webClient.get()
            .uri("/management/inventory")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(InventoryResponse.class);
    }

    @Test
    void givenEmptyData_whenInventory_thenEmptyResult() {
        Mockito.when(productEntityRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        webClient.get()
            .uri("/management/inventory")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void givenExistsData_whenSellProduct_thenSuccess() {
        Mockito.when(productEntityRepositoryMock.findById(100L)).thenReturn(Optional.of(ProductEntity.builder().id(100L).product(Product.ACCESSORIES).company(Company.DC).build()));
        Mockito.when(stockEntityRepositoryMock.findByProductId(100L)).thenReturn(Optional.of(StockEntity.builder().id(100L).quantity(BigInteger.valueOf(13)).build()));
        Mockito.doNothing().when(stockEntityRepositoryMock).updateStock(Mockito.anyLong(), Mockito.any(BigInteger.class));

        webClient.put()
            .uri("/management/sell/100/quantity/12")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ProductInformation.class);

        Mockito.verify(stockEntityRepositoryMock, Mockito.times(1)).updateStock(100L, BigInteger.valueOf(1));
    }

    @Test
    void givenNotExistenceData_whenSellProduct_thenBadRequest() {
        Mockito.when(productEntityRepositoryMock.findById(100L)).thenReturn(Optional.of(ProductEntity.builder().id(100L).product(Product.ACCESSORIES).company(Company.DC).build()));
        Mockito.when(stockEntityRepositoryMock.findByProductId(100L)).thenReturn(Optional.of(StockEntity.builder().id(100L).quantity(BigInteger.valueOf(10)).build()));
        Mockito.doNothing().when(stockEntityRepositoryMock).updateStock(Mockito.anyLong(), Mockito.any(BigInteger.class));

        webClient.put()
            .uri("/management/sell/100/quantity/12")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();

    }

    @Test
    void givenEmptyData_whenSellProduct_thenEmptyResult() {
        Mockito.when(productEntityRepositoryMock.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(stockEntityRepositoryMock.findByProductId(100L)).thenReturn(Optional.of(StockEntity.builder().id(100L).quantity(BigInteger.valueOf(13)).build()));
        Mockito.doNothing().when(stockEntityRepositoryMock).updateStock(Mockito.anyLong(), Mockito.any(BigInteger.class));

        webClient.put()
            .uri("/management/sell/100/quantity/12")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    void givenExistsData_whenBuyProduct_thenSuccess() {
        Mockito.when(productEntityRepositoryMock.findById(100L)).thenReturn(Optional.of(ProductEntity.builder().id(100L).product(Product.ACCESSORIES).company(Company.DC).build()));
        Mockito.when(stockEntityRepositoryMock.findByProductId(100L)).thenReturn(Optional.of(StockEntity.builder().id(100L).quantity(BigInteger.valueOf(13)).build()));
        Mockito.doNothing().when(stockEntityRepositoryMock).updateStock(Mockito.anyLong(), Mockito.any(BigInteger.class));

        webClient.put()
            .uri("/management/buy/100/quantity/12")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(ProductInformation.class);

        Mockito.verify(stockEntityRepositoryMock, Mockito.times(1)).updateStock(100L, BigInteger.valueOf(25));
    }

    @Test
    void givenEmptyData_whenBuyProduct_thenEmptyResult() {
        Mockito.when(productEntityRepositoryMock.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(stockEntityRepositoryMock.findByProductId(100L)).thenReturn(Optional.of(StockEntity.builder().id(100L).quantity(BigInteger.valueOf(13)).build()));
        Mockito.doNothing().when(stockEntityRepositoryMock).updateStock(Mockito.anyLong(), Mockito.any(BigInteger.class));

        webClient.put()
            .uri("/management/buy/100/quantity/12")
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
    }

}
