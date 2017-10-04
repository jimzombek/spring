package com.jmzombe.nhl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is the main entry point and configuration for the NHL App
 * annotated with {@link SpringBootApplication} to enable configuration, 
 * classpath component scanning, etc.
 */
@SpringBootApplication
@EnableSwagger2
public class NHLApp {
    public static void main(String[] args) {
        SpringApplication.run(NHLApp.class, args);
    }

    /**
     * Configure the Docket instance to document with Swagger 2, and to provide
     * API information based on the ApiInfo returned from the apiInfo method.
     *
     * @return the configured Docket instance
     */
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Provides API information when the user navigates to the Swagger UI page.
     *
     * @return ApiInfo for the application
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("NHLApp API")
                .description("The NHLApp API allows you to perform CRUD operations on the Player and Team resources.")
                .contact(new Contact("Jim Zombek", "https://github.com/jimzombek/spring-mongo", "jimzombek@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/jimzombek/spring-mongo/blob/master/LICENSE")
                .version("1.0")
                .build();
    }

    /**
     * Setting serialization options for the REST endpoints.
     */
    @Component
    @Primary
    public class CustomObjectMapper extends ObjectMapper {
        private static final long serialVersionUID = 1L;

		public CustomObjectMapper() {
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            enable(SerializationFeature.INDENT_OUTPUT);
        }
    }
}
