package com.electronic.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(getApiInfo());


        docket.securityContexts(Arrays.asList(getSecurityContext()));
        docket.securitySchemes(Arrays.asList(getSchemes()));

        ApiSelectorBuilder select = docket.select();
        select.apis(RequestHandlerSelectors.any());
        select.paths(PathSelectors.any());
        Docket build = select.build();
        return build;
    }


    private SecurityContext getSecurityContext() {

        SecurityContext context = SecurityContext
                .builder()
                .securityReferences(getSecurityReferences())
                .build();
        return context;
    }

    private List<SecurityReference> getSecurityReferences() {
        AuthorizationScope[] scopes = {new AuthorizationScope("Global", "Access Every Thing")};
        return Arrays.asList(new SecurityReference("JWT", scopes));

    }

    private ApiKey getSchemes() {
        return new ApiKey("JWT", "Authorization", "header");
    }


    private ApiInfo getApiInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Electronic Cloud Store Backend : APIS ",
                "This is backend of Electronic Cloud Store by Vinay Shaw",
                "1.0.0V",
                "",
                new Contact("Vinay", "https://www.instagram.com/_vinayshaw_/", "vinayshaw544@gmail.com"),
                "License of APIS",
                "",
                new ArrayDeque<>()
        );

        return apiInfo;

    }


}