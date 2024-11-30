package com.example.microsoft.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Richa Pokhrel
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "azure.ad")
public class AzureAdProperties {

    private String clientId;
    private String clientSecret;
    private String tenantId;
}
