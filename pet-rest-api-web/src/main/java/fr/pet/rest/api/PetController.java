package fr.pet.rest.api;

import fr.pet.rest.core.model.Pet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@RestController
@EnableAutoConfiguration
public class PetController {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }

    @RequestMapping("/pet/add")
    Pet add(Pet pet) {
        return new Pet();
    }
}
