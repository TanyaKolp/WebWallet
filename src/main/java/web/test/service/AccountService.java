package web.test.service;

import web.test.model.Account;
import web.test.model.ServicesSection;

import java.util.List;

/**
 * Created by tania on 24.04.17.
 */
public interface AccountService {
    public Account getAccountById(Integer id);
    public Account getAccountByUserId(Integer id);
    public List<ServicesSection> getListForAccount(Account account);
}
