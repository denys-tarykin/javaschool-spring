package com.springapp.domain_objects;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 3/3/14.
 */
@Entity
@Table(name = "tags")
public class Tag extends IdentifiableEntity {
    private String tag;
    private Set<Product> products = new HashSet<Product>(0);

    @Column(name = "tag", length = 100, nullable = false, unique = true)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @ManyToMany(mappedBy = "tags")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
