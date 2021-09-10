package com.gym.workout.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gym.workout"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
//                .globalOperationParameters(
//                        Arrays.asList(
//                                new ParameterBuilder()
//                                        .name("Authorization")
//                                        .description("Header para Token JWT")
//                                        .modelRef(new ModelRef("string"))
//                                        .parameterType("header")
//                                        .required(false)
//                                        .build()));
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Produtos API REST",
                "API REST de cadastro de produtos.",
                "1.0",
                "Terms of Service",
                new Contact("Ismael Oliveira", "https://www.youtube.com/channel/UCczCRXH8-PaQX42c4WESTDg",
                        "madeonroca@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
