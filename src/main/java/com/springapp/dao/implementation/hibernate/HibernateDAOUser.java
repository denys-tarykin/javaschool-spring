package com.springapp.dao.implementation.hibernate;

import com.springapp.dao.interfaces.DAOUser;
import com.springapp.domain_objects.User;
import com.springapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;

public class HibernateDAOUser extends HibernateDAOCRUD<User> implements DAOUser {

    protected Class getInnerClass() {
        return User.class;
    }

    public User getByName(String name) throws SQLException {
        Session session = null;
        //List<User> Info;
        User Info;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Info = session.createCriteria(User.class).add(Restrictions.eq("name", name)).list();
            Info = (User) session.createCriteria(User.class).add(Restrictions.eq("name", name)).uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return Info;
    }

    public User getForAuth(String name, String password) throws SQLException {
        Session session = null;
        //List<User> Info;
        User Info;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Info = session.createCriteria(User.class).add(Restrictions.eq("name", name)).list();
            Info = (User) session.createCriteria(User.class).add(Restrictions.eq("name", name))
                    .add(Restrictions.eq("password", password))
                    .uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Data error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return Info;
    }

}
