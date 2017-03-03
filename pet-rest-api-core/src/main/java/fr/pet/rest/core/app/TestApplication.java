package fr.pet.rest.core.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by TDERVILY on 03/03/2017.
 */
@ComponentScan
@EntityScan
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);
    }

}
