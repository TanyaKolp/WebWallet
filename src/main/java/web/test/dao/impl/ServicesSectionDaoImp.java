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

    @Override
    public ServicesSection getById(Integer id) {
        logger.info("getting by id");
        Session session = sessionFactory.getCurrentSession();
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
        logger.info("update #" + model.getId());
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(model);
    }

    @Override
    public void delete(ServicesSection model) {

    }

    @Override
    public List<String> getNames() {
        logger.info("getting names..");
        Session session = sessionFactory.getCurrentSession();
        List<String> names = (List<String>) session.createQuery("select ss.name from ServicesSection as ss").list();
        return names;
    }
}
