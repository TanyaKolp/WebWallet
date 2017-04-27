package web.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.test.dao.AccountDao;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.TypeService;
import web.test.service.AccountService;

import java.util.List;

/**
 * Created by tania on 24.04.17.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getAccountById(Integer id) {
        return null;
    }

    @Override
    public Account getAccountByUserId(Integer id) {
        return accountDao.getAccountByUserId(id);
    }

    @Override
    public List<ServicesSection> getListForAccount(Account account) {

        return null;
    }
    private void setAllWorth(List<ServicesSection> servicesSections) {
        for (ServicesSection ss : servicesSections) {
            Double allWorth = 0.0;
            for (TypeService typeService : ss.getTypeServices()) {
                allWorth = allWorth + typeService.getWorth();
            }
            ss.setAllWorth(allWorth);
        }

    }
}
