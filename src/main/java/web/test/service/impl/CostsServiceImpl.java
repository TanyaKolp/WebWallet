package web.test.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    final static Logger logger = Logger.getLogger(CostsServiceImpl.class);
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TypeServiceDao typeServiceDao;
    @Autowired
    private ServicesSectionDao servicesSectionDao;


    @Override
    @Transactional
    public Account getAccountByUserId(Integer id) {
        return accountDao.getAccountByUserId(id);
    }


    @Override
    @Transactional
    public List<ServicesSection> getSectionsForAccount(Account account) {
        logger.info("load list of sections");
        List<ServicesSection> result = servicesSectionDao.getSectionsByAccountID(account.getId());
        setAllWorth(result);
        return result;
    }

    @Override
    public List<TypeService> getTypes(Integer sectionID) {

        return typeServiceDao.getTypesBySectionId(sectionID);
    }

    @Override
    public void createAccount(Account account) {
        accountDao.create(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    @Transactional
    private void setAllWorth(List<ServicesSection> servicesSections) {
        logger.info("SETTING allWorth..");
        List<TypeService> typesBySectionId = null;
        for (ServicesSection ss : servicesSections) {
            Double allWorth = 0.0;
            logger.info("get types by section id #" + ss.getId());
            typesBySectionId = typeServiceDao.getTypesBySectionId(ss.getId());
            for (TypeService typeService : typesBySectionId) {
                allWorth = allWorth + typeService.getWorth();
            }
            logger.info("set worth = "+allWorth+" for section #" + ss.getId());
            ss.setAllWorth(allWorth);
        }
//        for (ServicesSection ss : servicesSections){
//            servicesSectionDao.update(ss);
//        }
    }
}
