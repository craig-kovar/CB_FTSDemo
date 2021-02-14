package com.cb.ftsdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class AppConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cb.ftsdemo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo metaInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        return apiInfoBuilder.title("FTS Demo")
                .contact(new Contact("Couchbase",null,"craig.kovar@couchbase.com"))
                .description("FTS Demo Rest Services")
                .version("1.0.0")
                .build();

    }

}
