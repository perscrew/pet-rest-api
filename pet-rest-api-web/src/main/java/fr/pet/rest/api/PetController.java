package fr.pet.rest.api;

import fr.pet.rest.core.dao.CategoryRepository;
import fr.pet.rest.core.dao.PetRepository;
import fr.pet.rest.core.model.Category;
import fr.pet.rest.core.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetRepository petRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PetController(PetRepository petRepository, CategoryRepository categoryRepository) {
        this.petRepository = petRepository;
        this.categoryRepository = categoryRepository;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Pet pet) {
        return savePet(pet);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Pet pet) {
        if (petRepository.findOne(pet.getId()) != null) {
            return savePet(pet);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Page> list(Pageable pageable) {
        return new ResponseEntity<>(this.petRepository.findAll(pageable), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{petId}", method = RequestMethod.GET)
    public ResponseEntity<Pet> getById(@PathVariable Long petId) {
        return  new ResponseEntity<>(petRepository.findOne(petId), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{petId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long petId) {
        petRepository.delete(petId);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> savePet(Pet pet) {
        // Fetch pet category from id
        Category category = this.categoryRepository.findOne(pet.getCategory().getId());

        if (category == null)
            return ResponseEntity.noContent().build();

        // Save pet
        pet.setCategory(category);
        return new ResponseEntity<>(petRepository.save(pet), HttpStatus.OK);
    }
}
