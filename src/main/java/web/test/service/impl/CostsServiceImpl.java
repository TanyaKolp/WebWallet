package web.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.test.dao.AccountDao;
import web.test.dao.ServicesSectionDao;
import web.test.dao.TypeServiceDao;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.TypeService;
import web.test.service.CostsService;

import java.util.List;

/**
 * Created by tania on 26.04.17.
 */
@Service("costsService")
public class CostsServiceImpl implements CostsService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TypeServiceDao typeServiceDao;
    @Autowired
    private ServicesSectionDao servicesSectionDao;


    @Override
    public Account getAccountByUserId(Integer id) {
        return accountDao.getAccountByUserId(id);
    }


    @Override
    public List<ServicesSection> getSectionsForAccount(Account account) {
        List<ServicesSection> result = servicesSectionDao.getSectionsByAccountID(account.getId());
        setAllWorth(result);
        return result;
    }

    @Override
    public List<TypeService> getTypes(Integer sectionID) {

        return typeServiceDao.getTypesBySectionId(sectionID);
    }

    private void setAllWorth(List<ServicesSection> servicesSections) {
        List<TypeService> typesBySectionId = null;
        for (ServicesSection ss : servicesSections) {
            Double allWorth = 0.0;
            typesBySectionId = typeServiceDao.getTypesBySectionId(ss.getId());
            for (TypeService typeService : typesBySectionId) {
                allWorth = allWorth + typeService.getWorth();
            }
            ss.setAllWorth(allWorth);
        }
    }
}
