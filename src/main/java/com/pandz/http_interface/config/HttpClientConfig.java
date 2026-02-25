package com.pandz.http_interface.config;

import com.pandz.http_interface.model.http.ParameterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {
    @Bean
    ParameterClient parameterClient(@Value("${simulator.base-url}") String baseUrl) {

        RestClient restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .build();

        return HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(restClient))
            .build()
            .createClient(ParameterClient.class);
    }
}
