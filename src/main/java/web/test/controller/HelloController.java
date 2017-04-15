package web.test.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.test.hibernate.HibUtil;
import web.test.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tania on 12.04.17.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/")
    public ModelAndView homepage() {
        return new ModelAndView("index");
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
        List<User> list = getUsersList();
        //return back to users.jsp
        ModelAndView model = new ModelAndView("users");
        model.addObject("lists", list);

        return model;
    }

    public List<User> getUsersList() {

        SessionFactory sessionFactory = HibUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<User> usersList = (ArrayList<User>) session.createCriteria(User.class).list();
        session.close();
//        sessionFactory.close();
        return usersList;
    }

}
