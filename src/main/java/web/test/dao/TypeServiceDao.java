package web.test.dao;

import web.test.model.TypeService;

import java.util.List;

/**
 * Created by tania on 26.04.17.
 */
public interface TypeServiceDao extends ItemDao<TypeService> {
     public List<TypeService> getTypesBySectionId(Integer sectionID);
}
