package fr.pet.rest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@Configuration
@ComponentScan(basePackages="fr.pet.rest.api")
@EnableAutoConfiguration
@EntityScan(basePackages="fr.pet.rest.api")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
