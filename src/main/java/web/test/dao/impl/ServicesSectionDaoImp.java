package web.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.test.dao.ServicesSectionDao;
import web.test.model.ServicesSection;

import java.util.List;

/**
 * Created by tania on 25.04.17.
 */
@Repository
public class ServicesSectionDaoImp implements ServicesSectionDao {
    static Logger logger = Logger.getLogger(ServicesSectionDaoImp.class);

    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
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

    @Override
    @Transactional
    public ServicesSection getById(Integer id) {
        logger.info("getting by id");
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
    @Transactional
    public void update(ServicesSection model) {
        logger.info("update #" + model.getId());
        Session session = getSession();
        session.saveOrUpdate(model);
    }

    @Override
    public void delete(ServicesSection model) {

    }


    @Override
    @Transactional
    public List<ServicesSection> getSectionsByAccountID(Integer accountId) {
        logger.info("getting by accountID = "+accountId);
        Session session = getSession();
        List<ServicesSection> result = session.createQuery("from ServicesSection as ss where ss.account.id = "
                + accountId).list();
        return result;
    }
}
