package com.example.microsoft.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.example.microsoft.dto.request.AzureAdProperties;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author Richa Pokhrel
 */
@Slf4j
@Configuration
public class MicrosoftAPIConfig {

    @Autowired
    private AzureAdProperties azureAdProperties;

    /**
     * Configures and provides an authenticated GraphServiceClient instance.
     *
     * @return an authenticated GraphServiceClient for Microsoft Graph API requests
     */
    @Bean
    public GraphServiceClient<?> graphServiceClient() {
        // Create the ClientSecretCredential with Azure AD credentials
        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(azureAdProperties.getClientId())
                .clientSecret(azureAdProperties.getClientSecret())
                .tenantId(azureAdProperties.getTenantId())
                .build();

        // Set up the authentication provider with required permissions
        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(
                Collections.singletonList("https://graph.microsoft.com/.default"), clientSecretCredential);

        // Build and return the GraphServiceClient instance
        return GraphServiceClient
                .builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }

}
