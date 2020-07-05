package com.hibernate.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.hibernate.entity.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    // Property based configuration
    private static SessionFactory sessionJavaConfigFactory = null;

    // Connection for calling stored procedure
    private static Connection connection = null;

    private static Connection buildConnection() {
        Properties prop = new Properties();
        InputStream inputFile = null;
        try {
            inputFile = new FileInputStream("SQLSetting.txt");
            prop.load(inputFile);
            String url = "jdbc:sqlserver://localhost:1433;databaseName=" + prop.getProperty("db.dbname");
            String username = prop.getProperty("db.user");
            String pass = prop.getProperty("db.password");
            // Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLSV", "sa", "Long123ohio");
            Connection c = DriverManager.getConnection(url, username, pass);
            return c;
        }
        catch (Throwable ex) {
            // System.err.println("Initial Connection failed." + ex);
            System.err.println("Lỗi ở hàm buildConnection file HibernateUtil");
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        Properties prop = new Properties();
        InputStream inputFile = null;
        try {
            inputFile = new FileInputStream("SQLSetting.txt");
            prop.load(inputFile);
            String url = "jdbc:sqlserver://localhost:1433;databaseName=" + prop.getProperty("db.dbname");
            String username = prop.getProperty("db.user");
            String pass = prop.getProperty("db.password");

            Configuration configuration = new Configuration();

            // Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // props.put("hibernate.connection.url", "jdbc:sqlserver://localhost:1433;databaseName=QLSV");
            // props.put("hibernate.connection.username", "sa");
            // props.put("hibernate.connection.password", "Long123ohio");
            props.put("hibernate.connection.url", url);
            props.put("hibernate.connection.username", username);
            props.put("hibernate.connection.password", pass);
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            props.put("hibernate.id.new_generator_mappings", "false");

            configuration.setProperties(props);

            // we can set mapping file or class with annotation
            // addClass(SinhVien.class) will look for resource SinhVien.hbm.xml (which is not good)
            configuration.addAnnotatedClass(SinhVien.class);
            configuration.addAnnotatedClass(MonHoc.class);
            configuration.addAnnotatedClass(LopHoc.class);
            configuration.addAnnotatedClass(SinhVien_MonHoc.class);
            configuration.addAnnotatedClass(MonHoc_LopHoc.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            // System.out.println("Hibernate Java Config serviceRegistry created");
            System.out.println("Có vẻ đang ổn vãi lone nhé");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // System.err.println("Initial SessionFactory creation failed." + ex);
            System.err.println("Lỗi ở hàm buildSessionJavaConfigFactory file HibernateUtil");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) {
            sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        }
        return sessionJavaConfigFactory;
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = buildConnection();
        }
        return connection;
    }
}