package web.test.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import web.test.dao.AccountDao;
import web.test.dao.ServicesSectionDao;
import web.test.dao.TypeServiceDao;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.TypeService;
import web.test.model.User;
import web.test.service.CostsService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tania on 26.04.17.
 */
@Service("costsService")
@Transactional
public class CostsServiceImpl implements CostsService {

    final static Logger logger = Logger.getLogger(CostsServiceImpl.class);
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TypeServiceDao typeServiceDao;
    @Autowired
    private ServicesSectionDao servicesSectionDao;

    @Transactional
    @Override
    public Account getAccountByUserId(Integer id) {
        return accountDao.getAccountByUserId(id);
    }

    @Override
    @Transactional
    public void createAccount(Account account) {
        accountDao.create(account);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    @Override
    @Transactional
    public ModelAndView editAccount(Map<String, String> map, User user) {
        logger.info("edit account");
        ModelAndView modelAndView = new ModelAndView("welcome");
        Account account = user.getAccount();
        String accountType = map.get("accountType");
        account.setType(accountType);
        if (!map.get("accountBalance").isEmpty()) {
            try {
                account.setBalance(Double.valueOf(map.get("accountBalance")));
            } catch (NumberFormatException e) {
                modelAndView.setViewName("editAccountPage");
                modelAndView.addObject("error", "Not a number: field balance.");
                return modelAndView;
            }
        }
        if (!map.get("payrollDate").isEmpty()) {
            DateFormat format = new SimpleDateFormat("MM-dd");
            try {
                Date parsed = format.parse(map.get("payrollDate"));
                account.setPayrollDate(parsed);
            } catch (ParseException e) {
                modelAndView.setViewName("editAccountPage");
                modelAndView.addObject("error", "Wrong date.");
                return modelAndView;
            }
        }
        accountDao.update(account);
        modelAndView.addObject("account", account);
        modelAndView.addObject("userLogin", user.getLogin());
        return modelAndView;
    }

    @Override
    @Transactional
    public ModelAndView addCost(TypeService[] products, User user) {
        logger.info("adding " + products.length + " cost(s)");
        logger.info("Account" +"");
        ModelAndView modelAndView = new ModelAndView("welcome");
        Account account = user.getAccount();
        logger.info("Account" + account.getBalance());
        String errorMessage = checkInputAndBalance(products, account);
        modelAndView.addObject("account", account);
        if (errorMessage == null) {
            Double sum = 0.0;
            for (int i = 0; i < products.length; i++) {
                resetSection(products[i]);
                products[i].setAccount(account);
                typeServiceDao.create(products[i]);
                sum = sum + products[i].getWorth();
            }
            account.setBalance(account.getBalance() - sum);
            accountDao.update(account);

        } else {
            modelAndView.addObject("error", errorMessage);
        }
        return modelAndView;
    }

    private void resetSection(TypeService product) {
        ServicesSection section = servicesSectionDao.getByName(product.getServicesSection().getName());
        product.setServicesSection(section);
        logger.info("RESET section to " + section);
    }

    private TypeService getTypeService(Map<String, String> requestParam, Account account, Double worth) {
        TypeService typeService = new TypeService();
        typeService.setWorth(worth);
        typeService.setName(requestParam.get("name"));
        ServicesSection section = servicesSectionDao.getByName(requestParam.get("sectionType"));
        typeService.setAccount(account);
        typeService.setServicesSection(section);
        typeServiceDao.update(typeService);
        return typeService;
    }

    private String checkInputAndBalance(TypeService[] products, Account account) {
        Double sum = 0.0;
        for (int i = 0; i < products.length; i++) {
            sum = sum + products[i].getWorth();
        }
        if (account.getBalance() < sum) {
            return "Not enough money.";
        }
        return null;
    }


    @Override
    @Transactional
    public List<String> getCategories() {
        return servicesSectionDao.getNames();
    }

    @Override
    @Transactional
    public Map<String, Double> getMapCategoryAndSumByAccountID(Integer accountID) {
        return typeServiceDao.getMapOfSumAndWorthBySectionsForAccountId(accountID);
    }
}
