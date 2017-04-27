package web.test.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.test.dao.TypeServiceDao;
import web.test.model.TypeService;

import java.util.List;

/**
 * Created by tania on 26.04.17.
 */
@Repository
public class TypeServiceDaoImpl implements TypeServiceDao {
    @Autowired
    public SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TypeService getById(Integer id) {
        return null;
    }

    @Override
    public List<TypeService> getAll() {
        return null;
    }

    @Override
    public void create(TypeService model) {
        Session session = getSession();
        session.save(model);
    }

    @Override
    public void update(TypeService model) {

    }

    @Override
    public void delete(TypeService model) {

    }

    @Override
    public List<TypeService> getTypesBySectionId(Integer sectionID) {
        List<TypeService> typeList = null;
        Session session = getSession();
        typeList = (List<TypeService>) session.createQuery("from TypeService as t where t.servicesSection.id = " + sectionID).list();
        return typeList;
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
}
