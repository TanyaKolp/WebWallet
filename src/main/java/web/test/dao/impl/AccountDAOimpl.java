package web.test.dao.impl;

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
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    @Transactional
    public Account getAccountByUserId(Integer id) {
        Account account;
        Session session = getSession();
        account = (Account) session.createQuery("from Account as ac where ac.user.id = " + id).uniqueResult();

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

    }

    @Override
    public void update(Account model) {

    }

    @Override
    public void delete(Account model) {

    }
}
