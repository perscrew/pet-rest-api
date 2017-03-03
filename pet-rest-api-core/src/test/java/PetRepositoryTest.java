import fr.pet.rest.core.app.TestApplication;
import fr.pet.rest.core.dao.CategoryRepository;
import fr.pet.rest.core.dao.PetRepository;
import fr.pet.rest.core.model.Category;
import fr.pet.rest.core.model.Pet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Created by TDERVILY on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PetRepository petRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public static final String DOLPHIN = "dolphin";

    @Test
    public void getOnePet() throws Exception {
        Pet one = petRepository.findOne(1L);
        assertNotNull(one);
    }

    @Test
    public void createPet() throws Exception {
        Optional<Category> fish =
                StreamSupport.stream(categoryRepository.findAll().spliterator(), false).
                        filter(category -> category.getName().equals("Fish")).findFirst();
        assertNotNull(fish.get());
        entityManager.persist(new Pet(DOLPHIN, 3, fish.get()));
        assertNotNull(petRepository.findByname(DOLPHIN));
    }

    @Test
    public void updatePet() throws Exception {
        Pet one = petRepository.findOne(1L);
        assertNotNull(one);
        one.setName(one.getName() + "updated");
        petRepository.save(one);
        Pet updated = petRepository.findOne(1L);
        assertEquals(one.getName(), updated.getName());
    }

    @Test
    public void deletePet() throws Exception {
        petRepository.delete(2L);
        assertNull(petRepository.findOne(2L));
    }

    @Test
    public void getByPage() throws Exception {
        Page<Pet> pageOne = petRepository.findAll(new PageRequest(1, 10));
        assertThat(pageOne.getTotalElements()).isGreaterThan(0);
    }
}
