package com.springapp.domain_objects;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 3/2/14.
 */

@Entity
@Table(name = "products")
public class Product extends IdentifiableEntity {
    private String name;
    private BigDecimal price;
    private String description;
    private Set<Category> categories = new HashSet<Category>(0);
    private Set<Tag> tags = new HashSet<>(0);

    @Column(name = "name", length = 80, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_to_category", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "category_id",
                    nullable = false, updatable = false) })
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @ManyToMany(mappedBy = "products")
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
