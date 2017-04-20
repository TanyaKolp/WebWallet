package web.test.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.test.dao.UserDao;
import web.test.model.User;
import web.test.service.UserService;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    //    protected static Logger logger = Logger.getLogger("service");
    @Autowired
    private UserDao userDao;

    public void create(User user) {
        userDao.create(user);
    }

    /**
     * Retrieves all persons
     * Получает лист всех персон
     *
     * @return a list of persons
     */
    public List<User> getAll() {
        return userDao.getAll();
    }
}
