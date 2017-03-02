package fr.pet.rest.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@Configuration
@ComponentScan(basePackages="fr.pet.rest.api, fr.pet.rest.model, fr.pet.rest.core.dao")
@EnableAutoConfiguration
@EntityScan(basePackages="fr.pet.rest.api, fr.pet.rest.model, fr.pet.rest.core.dao")
@EnableJpaRepositories("fr.pet.rest.core.dao")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
