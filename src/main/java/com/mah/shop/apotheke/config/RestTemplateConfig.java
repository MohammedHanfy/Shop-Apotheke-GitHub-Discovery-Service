package com.mah.shop.apotheke.config;

import com.mah.shop.apotheke.exceptions.RestTemplateResponseErrorHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Autowired
    CloseableHttpClient httpClient;

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
