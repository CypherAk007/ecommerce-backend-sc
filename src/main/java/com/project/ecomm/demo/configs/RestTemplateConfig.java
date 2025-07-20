package com.project.ecomm.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // let spring know its config class -> spring-config-processor pom.xml
public class RestTemplateConfig {

    @Bean //we annotate with @Bean as RestTemplate(3rdParty) -> like db idanywhere
    // is not editable...
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
