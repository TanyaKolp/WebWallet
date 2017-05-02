package web.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    static Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void create(User user) {
        Session session = getSession();
        session.save(user);
    }

    @Override
    public void update(User model) {
        Session session = getSession();
        session.saveOrUpdate(model);
        logger.info("updated " + model.getLogin());
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        Session session = getSession();
        logger.info("create query - get all users");
        return session.createQuery("from User").list();
    }

    @Override
    public User getUserByName(String name) {
        Session session = getSession();
        logger.info("create query - get by name");
        User user = (User) session.createQuery("from User as u where u.name = '" + name + "'").uniqueResult();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = getSession();
        logger.info("create query - get by login");
        User user = (User) session.createQuery("from User as u where u.login = '" + login + "'").uniqueResult();

        return user;
    }

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            logger.info("get current session ");
        } catch (HibernateException e) {
            logger.info("open session by hand");
            session = sessionFactory.openSession();
        }
        return session;
    }

}
