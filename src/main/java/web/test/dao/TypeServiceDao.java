package web.test.dao;

import web.test.model.TypeService;

import java.util.List;
import java.util.Map;

/**
 * Created by tania on 26.04.17.
 */
public interface TypeServiceDao extends ItemDao<TypeService> {
     public List<TypeService> getTypesByAccountId(Integer accountID);
     public Map<String,Double> getMapOfSumAndWorthBySectionsForAccountId(Integer accountID);

}
