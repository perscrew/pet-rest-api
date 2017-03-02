package fr.pet.rest.api;

import fr.pet.rest.core.dao.CategoryRepository;
import fr.pet.rest.core.dao.PetRepository;
import fr.pet.rest.core.model.Category;
import fr.pet.rest.core.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@RestController
@RequestMapping("/pet")
@EnableAutoConfiguration
public class PetController {

    private final PetRepository petRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PetController(PetRepository petRepository, CategoryRepository categoryRepository) {
        this.petRepository = petRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Pet pet) {
        return savePet(pet);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody Pet pet) {
        // Fetch pet category from id
        return savePet(pet);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public Page list(@RequestBody Pageable pageable) {
        return this.petRepository.findAll(pageable);
    }

    @RequestMapping(path = "/{petId}", method = RequestMethod.GET)
    public Pet get(@PathVariable Long petId) {
        return petRepository.findOne(petId);
    }

    private ResponseEntity<?> savePet(Pet pet) {
        // Fetch pet category from id
        Category category = this.categoryRepository.findOne(pet.getCategory().getId());

        if (category != null)
            return ResponseEntity.noContent().build();

        // Save pet
        Pet result = petRepository.save(new Pet(pet.getName(), pet.getQuantity(), category));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
