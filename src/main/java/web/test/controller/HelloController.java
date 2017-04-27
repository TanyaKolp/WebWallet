package web.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.test.model.Account;
import web.test.model.ServicesSection;
import web.test.model.User;
import web.test.service.AccountService;
import web.test.service.UserService;
import web.test.service.*;

import java.util.List;
import java.util.Map;


/**
 * Created by tania on 12.04.17.
 */
@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @Autowired
    private CostsService costsService;

    @RequestMapping(value = "/")
    public String homepage() {

        return "index";
    }

    @RequestMapping(value = "/a", method = RequestMethod.POST)
    public String showAccount(@RequestParam(value = "userName", required = true) String userName,
                              Map<String, Object> map) {
        System.out.println("userName= " + userName);
        User foundUser = userService.getUserByName(userName);
        Account account = costsService.getAccountByUserId(foundUser.getId());
        List<ServicesSection> sections = costsService.getSectionsForAccount(account);
        System.out.println("НННННН SECTION SECTION SECTION");
        System.out.println(sections);
        map.put("account", account);
        map.put("sections", sections);
        return "account";
    }

    @RequestMapping(value = "/welcome")
    public ModelAndView helloWorld() {
        System.out.println("MY!!!!!!!!!!!!!!!!!!!!!!!!");
        String message = "<br><div style='text-align:center;'>"
                + "<h3>Hello World, TEST</h3>This message is coming from HelloController.java</div><br><br>";
        return new ModelAndView("welcome", "message", message);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        List<User> list = userService.getAll();
        //return back to users.jsp
        ModelAndView model = new ModelAndView("users");
        model.addObject("lists", list);

        return model;
    }
}
