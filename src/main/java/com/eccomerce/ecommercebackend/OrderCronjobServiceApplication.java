package com.eccomerce.ecommercebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderCronjobServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderCronjobServiceApplication.class, args);
    }
}