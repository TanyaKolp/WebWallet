package web.test.dao;

import web.test.model.Account;

/**
 * Created by tania on 24.04.17.
 */
public interface AccountDao extends ItemDao<Account>{
    public Account getAccountByUserId(Integer id);
}
