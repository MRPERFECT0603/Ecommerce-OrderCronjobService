package com.eccomerce.ecommercebackend.Retry;

import com.eccomerce.ecommercebackend.Clients.OrderServiceClient;
import com.eccomerce.ecommercebackend.Configs.RepoProperties;
import com.eccomerce.ecommercebackend.Observabilities.RetryLogger;
import com.eccomerce.ecommercebackend.repository.OrderReadRepository;
import org.springframework.stereotype.Component;

@Component
public class RetryExecutor {

    private final OrderReadRepository repository;
    private final OrderServiceClient client;
    private final RetryLogger logger;
    private final RepoProperties repoProperties;

    public RetryExecutor(
            OrderReadRepository repository,
            OrderServiceClient client,
            RetryLogger logger,
            RepoProperties repoProperties
    ) {
        this.repository = repository;
        this.client = client;
        this.logger = logger;
        this.repoProperties = repoProperties;
    }

    public void retryPendingOrders() {

        var pendingOrders =
                repository.findPendingOrders(repoProperties.getBatchSize());

        for (var order : pendingOrders) {
            logger.logRetry(order.getOrderId());
            client.retryCreateOrder(order.getUserId(), order.getOrderValue());
        }

        logger.logCronEnd(pendingOrders.size());
    }
}