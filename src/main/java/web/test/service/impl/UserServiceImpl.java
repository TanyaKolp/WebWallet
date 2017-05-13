package web.test.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import web.test.dao.AccountDao;
import web.test.dao.UserDao;
import web.test.model.Account;
import web.test.model.User;
import web.test.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl implements UserService {

    static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;

    public void create(User user) {
        userDao.create(user);
    }

    /**
     * @return a list of users
     */
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    @Transactional
    public ModelAndView logIn(String userLogin, String userPassword) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = getUserByLogin(userLogin);
            if (user != null) {
                if (user.getPassword().contentEquals(userPassword)) {
                    modelAndView.setViewName("welcome");
                    modelAndView.addObject("userLogin", userLogin);
                    modelAndView.addObject("user", user);
                    Account account = accountDao.getAccountByUserId(user.getId());
                    modelAndView.addObject("account", account);
                } else {
                    modelAndView.setViewName("index");
                    modelAndView.addObject("error", "Wrong password!");
                }
            } else {
                modelAndView.setViewName("index");
                modelAndView.addObject("error", "Wrong login!");
            }
        } catch (NonUniqueResultException e) {
            modelAndView.setViewName("index");
            modelAndView.addObject("error", "Wrong login!");
        }
        return modelAndView;
    }

    @Override
    @Transactional
    public ModelAndView singUp(String userLogin, String userPassword, String confirm) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userDao.getUserByLogin(userLogin);
        if(user != null){
            modelAndView.setViewName("signUp");
            modelAndView.addObject("error", "Login is busy.");
            return modelAndView;
        }
        if (userPassword.contentEquals(confirm)) {
            user = new User(userLogin, userPassword);
            Account account = new Account();
            account.setUser(user);
            user.setAccount(account);
            userDao.create(user);
            accountDao.create(account);
            modelAndView.setViewName("welcome");
            modelAndView.addObject("userLogin", userLogin);
        } else {
            modelAndView.setViewName("signUp");
            modelAndView.addObject("error", "Password and Confirm password is not match");
        }
        return modelAndView;
    }


    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public ModelAndView editProfile(Map<String, String> map, User user) {
        logger.info("edit user");
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("account", user.getAccount());
        if (!map.get("name").isEmpty()) {
           user.setName(map.get("name"));
        }
        if(!map.get("email").isEmpty()){
            user.setEmail(map.get("email"));
        }
        if (!map.get("birthday").isEmpty()) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsed = format.parse(map.get("birthday"));
                user.setBirthday(parsed);
            } catch (ParseException e) {
                modelAndView.setViewName("editProfilePage");
                modelAndView.addObject("error", "Wrong date.");
                return modelAndView;
            }
        }
        userDao.update(user);
        modelAndView.addObject("userLogin", user.getLogin());
        return modelAndView;
    }
}
