package web.test.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.test.dao.UserDao;
import web.test.model.User;

import java.util.List;

/**
 * Created by tania on 15.04.17.
 */
@Repository
public class UserDAOImpl implements UserDao {
    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    @Transactional
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        session.persist(user);
//        tx.commit();
//        session.close();

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<User> getAll() {

//        Session session = sessionFactory.openSession();
//        List<User> personList = session.createQuery("from User").list();
//        session.close();
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

}
