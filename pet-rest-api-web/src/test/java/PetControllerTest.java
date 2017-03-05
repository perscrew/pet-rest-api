import fr.pet.rest.app.Application;
import fr.pet.rest.core.dao.CategoryRepository;
import fr.pet.rest.core.dao.PetRepository;
import fr.pet.rest.core.model.Category;
import fr.pet.rest.core.model.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by TDERVILY on 02/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class PetControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    private Pet pet;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PetRepository petRepository;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
        Category dogCategory = new Category(1L, "dog");
        categoryRepository.save(dogCategory);
        petRepository.save(new Pet("Labrador chocolate", 2, dogCategory));
        petRepository.save(new Pet("Golden retriever", 2, dogCategory));
        pet = petRepository.findOne(1L);
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void getPetDetail() throws Exception {
        mockMvc.perform(get("/pet/1")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Labrador chocolate")))
                .andExpect(jsonPath("$.quantity", is(3)));
    }

    @Test
    public void addPet() throws Exception {
        mockMvc.perform(post("/pet")
                .content(json(new Pet("red dog", 10, new Category(1L))))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePet() throws Exception {
        Pet pet = petRepository.findOne(1L);
        Pet petUpd = new Pet(pet.getName() + "updated", pet.getQuantity(), new Category(pet.getId()));
        petUpd.setId(pet.getId());

        mockMvc.perform(put("/pet")
                .content(json(petUpd))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePet() throws Exception {
        mockMvc.perform(delete("/pet/" + pet.getId())
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/pet/list?page=1&size=10"))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
