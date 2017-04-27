package web.test.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.test.dao.ServicesSectionDao;
import web.test.model.ServicesSection;

import java.util.List;

/**
 * Created by tania on 25.04.17.
 */
@Repository
public class ServicesSectionDaoImp implements ServicesSectionDao {
    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public ServicesSection getById(Integer id) {
        Session session = getSession();
        ServicesSection result = (ServicesSection) session.createQuery("from ServicesSection where id = " + id).uniqueResult();
        return result;
    }

    @Override
    public List<ServicesSection> getAll() {
        return null;
    }

    @Override
    public void create(ServicesSection model) {

    }

    @Override
    public void update(ServicesSection model) {

    }

    @Override
    public void delete(ServicesSection model) {

    }

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public List<ServicesSection> getSectionsByAccountID(Integer accountId) {
        Session session = getSession();
        List<ServicesSection> result = session.createQuery("from ServicesSection as ss where ss.account.id = "
                + accountId).list();
        return result;
    }
}
