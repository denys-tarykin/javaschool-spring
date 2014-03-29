package com.springapp.domain_objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alex on 2/20/14.
 */
@MappedSuperclass
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class IdentifiableEntity implements BaseDomainObject, Serializable {

    private int id;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return true;
    }

}
