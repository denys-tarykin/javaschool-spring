package com.springapp.dao.interfaces;

import com.springapp.domain_objects.Tag;

import java.sql.SQLException;

public interface DAOTag extends DAOCRUD<Tag> {

    public Tag getByTag(String tag) throws SQLException;
}
