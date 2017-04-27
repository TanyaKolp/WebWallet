package web.test.dao;

import web.test.model.User;

import java.util.List;

/**
 * Created by tania on 15.04.17.
 */

public interface UserDao extends ItemDao<User>{
    public User getUserByName(String name);

}
