package fr.pet.rest.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@Configuration
@ComponentScan(basePackages="fr.pet.rest.api")
@EntityScan(basePackages="fr.pet.rest.core.model")
@EnableJpaRepositories(basePackages="fr.pet.rest.core.dao")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("petShop")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/pet.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Swagger Pet Shop")
                .description("Spring REST Swagger Pet Shop")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("Thibaud Dervily")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}
