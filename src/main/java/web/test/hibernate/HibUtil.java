package web.test.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import web.test.model.User;

import java.io.File;

/**
 * Created by tania on 11/16/16.
 */
public class HibUtil {

    private static SessionFactory factory;

    static {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/GasStations");
        cfg.setProperty("hibernate.connection.username", "root" );
        cfg.setProperty("hibernate.connection.password", "rumin" );
        cfg.setProperty("hibernate.show_sql", "true" );
        cfg.setProperty("hibernate.hbm2ddl.auto", "update" );

        cfg.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        factory = cfg.buildSessionFactory(builder.build());
    }
    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
