package com.monitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@Profile("prod")
@PropertySource("classpath:prod.properties")
public class ProdConfig {

    @Bean
    public String jsonURL() {
        log.info("Using production URL");
        return "${jsonurl}";
    }

    @Bean
    public String categoryURL() {
        return "${categoryURL}";
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
