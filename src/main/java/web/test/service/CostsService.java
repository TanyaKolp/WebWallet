package web.test.service;

import org.springframework.web.servlet.ModelAndView;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.TypeService;
import web.test.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by tania on 26.04.17.
 */
public interface CostsService {
    public Account getAccountByUserId(Integer id);
    public List<ServicesSection> getSectionsForAccount(Account account);
    public List<TypeService> getTypes(Integer sectionID);
    public void createAccount(Account account);
    public void updateAccount(Account account);
    public ModelAndView editAccount(Map<String,String> param, User user);
    ModelAndView addCost(Map<String, String> requestParam, User user);
}
