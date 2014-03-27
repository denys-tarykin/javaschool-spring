package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOTag;
import com.springapp.domain_objects.Tag;

public class HibernateDAOTag extends HibernateDAOCRUD<Tag> implements DAOTag {

    protected Class getInnerClass() {
        return Tag.class;
    }
}