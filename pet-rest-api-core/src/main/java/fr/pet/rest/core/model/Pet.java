package fr.pet.rest.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@Entity
public class Pet implements Serializable {

    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    Integer weight;

    Category category;

    public Pet() {
    }

    public Pet(Long id, String name, Integer weight, Category category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
