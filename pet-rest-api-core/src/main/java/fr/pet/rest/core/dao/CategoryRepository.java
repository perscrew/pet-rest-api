package fr.pet.rest.core.dao;

import fr.pet.rest.core.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by TDERVILY on 02/03/2017.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
