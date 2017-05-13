package web.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.test.dao.TypeServiceDao;
import web.test.model.TypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tania on 26.04.17.
 */
@Repository
public class TypeServiceDaoImpl implements TypeServiceDao {
    static Logger logger = Logger.getLogger(TypeServiceDaoImpl.class);
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
        Session session = sessionFactory.getCurrentSession();
        session.save(model);
    }

    @Override
    public void update(TypeService model) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(model);
    }

    @Override
    public void delete(TypeService model) {
    }

    @Override
    public List<TypeService> getTypesByAccountId(Integer accountID) {
        logger.info("getting types by account");
        Session session = sessionFactory.getCurrentSession();
        return (List<TypeService>) session.createQuery("from TypeService as t where t.account.id = " + accountID).list();
    }

    @Override
    public Map<String, Double> getMapOfSumAndWorthBySectionsForAccountId(Integer accountID) {
        Map<String, Double> map = new TreeMap<>();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ts.servicesSection.name as name, sum(ts.worth) as sum" +
                " from TypeService as ts where ts.account.id = " + accountID +
                " group by ts.servicesSection.id");
        query.setResultTransformer(Transformers.TO_LIST);
        List<List<Object>> rows = query.list();
        for (List<Object> row : rows) {
            map.put((String) row.get(0), (Double) row.get(1));
        }
        return map;
    }
}
