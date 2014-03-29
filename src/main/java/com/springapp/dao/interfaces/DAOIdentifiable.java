package com.springapp.dao.interfaces;

import com.springapp.domain_objects.IdentifiableEntity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Alex on 3/29/14.
 */
public interface DAOIdentifiable<T extends IdentifiableEntity> extends DAOCRUD<T> {
    public T getById(int id) throws SQLException;

    public Set getByIds(Collection<Integer> ids) throws SQLException;

    public Set getByIds(Integer[] ids) throws SQLException;

    public void remove(int id) throws SQLException;
}
