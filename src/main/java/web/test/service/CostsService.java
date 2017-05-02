package web.test.service;

import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.TypeService;

import java.util.List;

/**
 * Created by tania on 26.04.17.
 */
public interface CostsService {
    public Account getAccountByUserId(Integer id);
    public List<ServicesSection> getSectionsForAccount(Account account);
    public List<TypeService> getTypes(Integer sectionID);
    public void createAccount(Account account);
    public void updateAccount(Account account);

}
