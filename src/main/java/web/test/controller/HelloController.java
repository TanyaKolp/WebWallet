package web.test.controller;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.User;
import web.test.service.UserService;
import web.test.service.*;

import java.util.List;


/**
 * Created by tania on 12.04.17.
 */
@Controller
@SessionAttributes("user")
public class HelloController {
    static Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CostsService costsService;


    @RequestMapping(value = "/")
    public String homepage() {
        return "index";
    }

    @RequestMapping(value = "/signUp")
    public String signUpPae() {
        return "signUp";
    }

    @RequestMapping(value = "/accountInfo")
    public ModelAndView showAccount(@ModelAttribute("user") User user) {
        logger.info("POST: showAccount for user " + user.getName());
//        User foundUser = userService.getUserByName(userName);
        User foundUser = user;
        Account account = costsService.getAccountByUserId(foundUser.getId());
        logger.info("user account #" + account.getId());
        List<ServicesSection> sections = costsService.getSectionsForAccount(account);
        logger.info("received list of sections: " + sections);
        ModelAndView modelAndView = new ModelAndView("account");
        modelAndView.addObject("account", account);
        modelAndView.addObject("sections", sections);
        return modelAndView;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<User> list = userService.getAll();
        //return back to users.jsp
        ModelAndView model = new ModelAndView("users");
        model.addObject("lists", list);

        return model;
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public ModelAndView logIn(@RequestParam(value = "userLogin", required = true) String userLogin,
                              @RequestParam(value = "userPassword") String userPassword) {
        logger.info("log in user: " + userLogin);
        return userService.logIn(userLogin, userPassword);
    }

    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(value = "userLogin", required = true) String userLogin,
                                     @RequestParam(value = "userPassword") String userPassword,
                                     @RequestParam(value = "confirm") String confirm) {
        logger.info("sing up user: " + userLogin);
        return userService.singUp(userLogin, userPassword, confirm);
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    @Transactional
    public ModelAndView addAccount(@ModelAttribute("user") User user,
                                   @RequestParam(value = "accountType") String accountType,
                                   @RequestParam(value = "accountBalance") String accountBalance) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        Account account = null;
        try {
            account = new Account(new Double(accountBalance), accountType);
            account.setUser(user);
            costsService.createAccount(account);
        } catch (NumberFormatException e) {
            modelAndView.addObject("error", "Not a number: field balance.");
            return modelAndView;
        }
        user.setAccount(account);
        userService.update(user);
        modelAndView.addObject("account", account);
        modelAndView.addObject("userLogin", user.getLogin());
        return modelAndView;
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
