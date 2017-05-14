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
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public ModelAndView addCost(Map<String, String> requestParam, User user) {
        logger.info("adding cost..");
        ModelAndView modelAndView = new ModelAndView("welcome");
        Account account = user.getAccount();
        modelAndView.addObject("account", account);
        TypeService typeService = new TypeService();
        Double worth = null;
        try {
            worth = Double.valueOf(requestParam.get("worth"));
        } catch (NumberFormatException e) {
            modelAndView.addObject("error", "Not a number: field worth.");
            return modelAndView;
        }
        if (account.getBalance() >= worth) {
            typeService.setWorth(worth);
            account.setBalance(account.getBalance() - worth);
            accountDao.update(account);
        }else {
            modelAndView.addObject("error", "Not enough money.");
            return modelAndView;
        }
        ServicesSection section = servicesSectionDao.getByName(requestParam.get("sectionType"));
        typeService.setAccount(account);
        typeService.setName(requestParam.get("name"));
        typeService.setServicesSection(section);
        typeServiceDao.update(typeService);
        return modelAndView;
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
