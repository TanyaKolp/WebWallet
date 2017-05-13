package web.test.service;

import org.springframework.web.servlet.ModelAndView;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.TypeService;
import web.test.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tania on 26.04.17.
 */
public interface CostsService {
    public Account getAccountByUserId(Integer id);
    public void createAccount(Account account);
    public void updateAccount(Account account);
    public ModelAndView editAccount(Map<String,String> param, User user);
    ModelAndView addCost(Map<String, String> requestParam, User user);
    List<String> getCategories();
    List<Double> getSumsByCategory(Account account);
    Map<String,Double> getMapCategoryAndSumByAccountID(Integer accountID);
}
