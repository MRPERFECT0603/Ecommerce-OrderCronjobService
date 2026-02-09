package com.eccomerce.ecommercebackend.Clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Slf4j
@Component
public class OrderServiceClient {

    private final WebClient webClient;

    public OrderServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void retryCreateOrder(String userId, Double orderValue) {

        webClient.post()
                .uri("/order/")
                .bodyValue(new CreateOrderRequest(userId, orderValue))
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(r ->
                        log.info("Retry request sent successfully"))
                .doOnError(e ->
                        log.error("Retry request failed", e))
                .subscribe(); // fire-and-forget

    }

    record CreateOrderRequest(String userId, Double orderValue) {}
}