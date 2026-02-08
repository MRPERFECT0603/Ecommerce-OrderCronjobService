package com.eccomerce.ecommercebackend.Observabilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RetryLogger {

    public void logCronStart() {
        log.info("RETRY_CRON_START");
    }

    public void logRetry(String orderId) {
        log.info("RETRY_ORDER orderId={}", orderId);
    }

    public void logCronEnd(int count) {
        log.info("RETRY_CRON_END retriedCount={}", count);
    }
}