package web.test.dao;

import web.test.model.Model;

import java.util.List;

/**
 * Created by tania on 24.04.17.
 */
public interface ItemDao<T extends Model> {
    public T getById(Integer id);
    public List<T> getAll();
    public void create(T model);
    public void update(T model);
    public void delete(T model);

}
