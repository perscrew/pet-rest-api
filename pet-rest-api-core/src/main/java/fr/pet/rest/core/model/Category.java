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
public class Category implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String name;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
