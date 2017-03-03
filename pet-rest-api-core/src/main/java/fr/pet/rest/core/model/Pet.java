package fr.pet.rest.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by TDERVILY on 01/03/2017.
 */
@Entity
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PET")
    @SequenceGenerator(sequenceName = "S_PET", allocationSize = 1, name = "S_PET")
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAT_ID")
    Category category;

    public Pet() {
    }

    public Pet(String name, Integer quantity, Category category) {
        this.name = name;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
