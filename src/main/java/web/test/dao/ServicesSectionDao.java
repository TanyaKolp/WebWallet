package web.test.dao;

import web.test.model.ServicesSection;

import java.util.List;

/**
 * Created by tania on 25.04.17.
 */
public interface ServicesSectionDao extends ItemDao<ServicesSection>{
    List<String> getNames();
}
