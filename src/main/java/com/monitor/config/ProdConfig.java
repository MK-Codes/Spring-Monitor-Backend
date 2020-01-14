package com.monitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@Profile("prod")
@PropertySource("classpath:prod.properties")
public class ProdConfig {

    @Autowired
    private Environment environment;

    @Bean
    public String getJsonURL() {
        return environment.getProperty("jsonurl");
    }

    @Bean
    public String getJsonURLFilter() {
        return environment.getProperty("jsonurlfilter");
    }

    @Bean
    public String getRefreshPage() {
        return environment.getProperty("refreshto");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
