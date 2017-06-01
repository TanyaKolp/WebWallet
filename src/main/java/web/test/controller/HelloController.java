package web.test.controller;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.test.model.Account;
import web.test.model.TypeService;
import web.test.model.User;
import web.test.service.UserService;
import web.test.service.*;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @RequestMapping(value = "/welcome")
    public ModelAndView welcomePage(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("account", user.getAccount());
        return modelAndView;
    }

    @RequestMapping(value = "/signUp")
    public String signUpPage() {
        return "signUp";
    }

    @RequestMapping(value = "/account")
    public String editAccountPage() {
        return "editAccountPage";
    }

    @RequestMapping(value = "/profile")
    public String viewProfile() {
        return "profilePage";
    }

    @RequestMapping(value = "/edit")
    public String viewEditProfile() {
        return "editProfilePage";
    }

    @RequestMapping(value = "/costs")
    public ModelAndView showCosts(@ModelAttribute("user") User user) {
        logger.info("showCosts for user " + user.getName());
        Account account = costsService.getAccountByUserId(user.getId());
        logger.info("user account #" + account.getId());
        Map<String, Double> map = costsService.getMapCategoryAndSumByAccountID(account.getId());
        ModelAndView modelAndView = new ModelAndView("costsPage");
        modelAndView.addObject("account", account);
        modelAndView.addObject("sums", map);
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

    @RequestMapping(value = "/test", consumes = "application/json", method = RequestMethod.POST)
    public ModelAndView test(@RequestBody String userJson) {
        List<User> list = userService.getAll();
        //return back to users.jsp
        try {
            User[] users = new ObjectMapper().readValue(userJson, User[].class);
            for (int i = 0; i < users.length; i++) {
                logger.info("test ARRAY: " + i + " - " + users[i].getLogin());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView model = new ModelAndView("users");
        model.addObject("lists", list);

        return model;
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public ModelAndView logIn(@RequestParam(value = "userLogin", required = true) String userLogin,
                              @RequestParam(value = "userPassword") String userPassword) {

        logger.info("log in user: " + userLogin);
//        getUser(userLogin);
        return userService.logIn(userLogin, userPassword);
    }

    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(value = "userLogin", required = true) String userLogin,
                                     @RequestParam(value = "userPassword") String userPassword,
                                     @RequestParam(value = "confirm") String confirm) {
        logger.info("sing up user: " + userLogin);
        return userService.singUp(userLogin, userPassword, confirm);
    }

    @RequestMapping(value = "/addCost", consumes = "application/json", method = RequestMethod.POST)
    public ModelAndView addCost(@ModelAttribute("user") User user,
                                @RequestBody String costsJson) {
        try {
            logger.info("try parse json: " + costsJson);
            TypeService[] products = new ObjectMapper().readValue(costsJson, TypeService[].class);
            logger.info("prod "+products);
            for (int i = 0; i < products.length; i++) {
                logger.info("PRODUCT " + i + "\n\t values " + products[i].getName() +
                        ", " + products[i].getWorth() + " sec " + products[i].getServicesSection());
                String errorMessage = validateInput(products[i]);
                if (errorMessage != null) {
                    return new ModelAndView("welcome", "error", errorMessage);
                }
            }
            return costsService.addCost(products,user);
        } catch (Exception e) {
            logger.info("exp");
            logger.error("exp", e);
            return new ModelAndView("welcome", "error", "Not a number: field worth.");
        }
    }

    private String validateInput(TypeService product) {
        if (product.getWorth() == null || product.getName() == null) {
            return "Please fill in all fields";
        }
        return null;
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public ModelAndView editAccount(@ModelAttribute("user") User user,
                                    @RequestParam(value = "accountType", required = false) String accountType,
                                    @RequestParam(value = "accountBalance", required = false) String accountBalance,
                                    @RequestParam(value = "payrollMonth", required = false) String month,
                                    @RequestParam(value = "payrollDay", required = false) Integer day) {
        logger.info("editing account...");
        Map<String, String> requestParam = new HashMap();
        requestParam.put("accountType", accountType);
        requestParam.put("accountBalance", accountBalance);
        String date = month + "-" + day;
        requestParam.put("payrollDate", date);
        return costsService.editAccount(requestParam, user);
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute("user") User user,
                                    @RequestParam(value = "nameU", required = false) String nameU,
                                    @RequestParam(value = "emailU", required = false) String emailU,
                                    @RequestParam(value = "bdU", required = false) String bdU) {
        logger.info("edit profile...");
        Map<String, String> requestParam = new HashMap();
        requestParam.put("name", nameU);
        requestParam.put("email", emailU);
        requestParam.put("birthday", bdU);
        return userService.editProfile(requestParam, user);
    }

    @ModelAttribute("user")
    public User getUser() {
        logger.info("get user");
        return new User();
    }
}
