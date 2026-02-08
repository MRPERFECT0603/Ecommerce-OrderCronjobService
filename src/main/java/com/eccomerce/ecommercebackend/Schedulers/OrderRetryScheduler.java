package com.eccomerce.ecommercebackend.Schedulers;

import com.eccomerce.ecommercebackend.Configs.CronProperties;
import com.eccomerce.ecommercebackend.Observabilities.RetryLogger;
import com.eccomerce.ecommercebackend.Retry.RetryExecutor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderRetryScheduler {

    private final RetryExecutor retryExecutor;
    private final CronProperties cronProperties;
    private final RetryLogger logger;

    public OrderRetryScheduler(
            RetryExecutor retryExecutor,
            CronProperties cronProperties,
            RetryLogger logger
    ) {
        this.retryExecutor = retryExecutor;
        this.cronProperties = cronProperties;
        this.logger = logger;
    }

    @Scheduled(cron = "#{@cronProperties.expression}")
    @SchedulerLock(name = "orderRetryJob")
    public void run() {

        if (!cronProperties.isEnabled()) return;

        logger.logCronStart();
        retryExecutor.retryPendingOrders();
    }
}