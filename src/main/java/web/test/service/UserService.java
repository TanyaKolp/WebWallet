package web.test.service;

import web.test.model.User;

import java.util.List;

/**
 * Created by tania on 20.04.17.
 */
public interface UserService {
    public void create(User user);
    public List<User> getAll();
    public User getUserByName(String name);
}
