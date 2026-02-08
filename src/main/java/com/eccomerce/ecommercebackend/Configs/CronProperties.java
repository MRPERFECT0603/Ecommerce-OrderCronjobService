package com.eccomerce.ecommercebackend.Configs;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "order.cron")
public class CronProperties {
    private String Expression;
    private Boolean Enabled;

    public Boolean isEnabled(){
        return Enabled;
    }

}
