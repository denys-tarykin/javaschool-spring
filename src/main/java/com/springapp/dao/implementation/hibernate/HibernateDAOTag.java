package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOTag;
import com.springapp.domain_objects.Tag;
import com.springapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HibernateDAOTag extends HibernateDAOIdentifiable<Tag> implements DAOTag {

    protected Class getInnerClass() {
        return Tag.class;
    }

    public Tag getByTag(String tag) throws SQLException {
        Session session = null;
        Tag return_tag = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return_tag = (Tag) session.createCriteria(Tag.class).add(Restrictions.eq("tag", tag)).uniqueResult();

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return return_tag;
    }



    public Set<Tag> getByNames(Collection<String> names) throws SQLException {
        Session session = null;
        Set<Tag> return_data = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return_data = new HashSet<Tag>(session.createCriteria(this.getInnerClass()).add(Restrictions.in("tag", names)).list());

        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return return_data;
    }
}