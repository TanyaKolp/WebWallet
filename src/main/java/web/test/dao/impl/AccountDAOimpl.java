package web.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.test.dao.AccountDao;
import web.test.model.Account;

import java.util.List;

/**
 * Created by tania on 24.04.17.
 */
@Repository
public class AccountDAOimpl implements AccountDao {
    static Logger logger = Logger.getLogger(AccountDAOimpl.class);

    @Autowired
    public SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Account getAccountByUserId(Integer id) {
        logger.info("getting by userID...");
        Session session = sessionFactory.getCurrentSession();
        Account account = (Account) session.createQuery("from Account as ac where ac.user.id = " + id).uniqueResult();
        logger.info("got account for user #" + id);
        return account;
    }

    @Override
    public Account getById(Integer id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void create(Account model) {
        logger.info("creating..");
        Session session = sessionFactory.getCurrentSession();
        session.save(model);
        logger.info("created account #" + model.getId());
    }

    @Override
    public void update(Account model) {
        logger.info("updating...");
        Session session = sessionFactory.getCurrentSession();
        session.merge(model);
        logger.info("updated account #" + model.getId());
    }

    @Override
    public void delete(Account model) {

    }
}
