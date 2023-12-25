package com.example.demo.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class DynamoDbConfig {

    @Bean
    public DynamoDbClient dynamoDbClient(final DynamoDbProperties properties) {
        System.out.println("properties.getHost(): " + properties.getHost());
        System.out.println("properties.getRegion(): " + properties.getRegion());

        return DynamoDbClient.builder()
                .endpointOverride(URI.create(properties.getHost()))
                .region(Region.of(properties.getRegion()))
                .build();
    }
}
