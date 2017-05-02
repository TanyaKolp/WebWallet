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

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String signUpPage() {
        return "signUp";
    }

    @RequestMapping(value = "/account")
    public String editAccountPage() {
        return "editAccountPage";
    }

    @RequestMapping(value = "/costs")
    public ModelAndView showCosts(@ModelAttribute("user") User user) {
        logger.info("POST: showCosts for user " + user.getName());
        Account account = costsService.getAccountByUserId(user.getId());
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

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public ModelAndView editAccount(@ModelAttribute("user") User user,
                                    @RequestParam(value = "accountType", required = false) String accountType,
                                    @RequestParam(value = "accountBalance", required = false) String accountBalance,
                                    @RequestParam(value = "payrollDate", required = false) String date) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        Account account = user.getAccount();
        account.setType(accountType);
        if (!accountBalance.isEmpty()) {
            try {
                account.setBalance(Double.valueOf(accountBalance));
            } catch (NumberFormatException e) {
                modelAndView.setViewName("editAccountPage");
                modelAndView.addObject("error", "Not a number: field balance.");
                return modelAndView;
            }
        }
        if (!date.isEmpty()) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsed = format.parse(date);
                account.setPayrollDate(parsed);
            } catch (ParseException e) {
                modelAndView.setViewName("editAccountPage");
                modelAndView.addObject("error", "Wrong date.");
                return modelAndView;
            }
        }
        costsService.updateAccount(account);
        modelAndView.addObject("account", account);
        modelAndView.addObject("userLogin", user.getLogin());
        return modelAndView;
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
