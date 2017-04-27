package web.test.service;

import org.springframework.web.servlet.ModelAndView;
import web.test.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by tania on 20.04.17.
 */
public interface UserService {
    public List<User> getAll();
    public User getUserByName(String name);
    public User getUserByLogin(String login);
    ModelAndView logIn(String userLogin, String userPassword);
}
