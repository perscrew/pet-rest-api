import fr.pet.rest.core.app.Application;
import fr.pet.rest.core.dao.PetRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TDERVILY on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Test
    public void getOnePet() throws Exception {
        Assert.assertNotNull(petRepository.findOne(1L));
    }
}
