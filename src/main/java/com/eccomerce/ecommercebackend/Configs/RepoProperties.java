package com.eccomerce.ecommercebackend.Configs;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "retry")
public class RepoProperties {

    private int batchSize = 1000;
}
