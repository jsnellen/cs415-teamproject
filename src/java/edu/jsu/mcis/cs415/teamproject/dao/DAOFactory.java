package edu.jsu.mcis.cs415.teamproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class DAOFactory {

    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    private String url, username, password;

    private DataSource ds;

    public DAOFactory() throws DAOException {

        try {

            Context envContext = new InitialContext();
            Context initContext = (Context) envContext.lookup("java:/comp/env");
            ds = (DataSource) initContext.lookup("jdbc/db_pool");

        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }

    }

    public DAOFactory(String prefix) throws DAOException {

        DAOProperties properties = new DAOProperties(prefix);

        this.url = properties.getProperty(PROPERTY_URL);
        this.username = properties.getProperty(PROPERTY_USERNAME);
        this.password = properties.getProperty(PROPERTY_PASSWORD);

        try {
            ds = null;
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }

    }

    Connection getConnection() throws DAOException {

        Connection c = null;

        try {

            if (ds != null) {
                c = ds.getConnection();
            } else {
                c = DriverManager.getConnection(url, username, password);
            }

        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }

        return c;

    }

    public EventDAO getEventDAO() {
        return new EventDAO(this);
    }
    
    public UserDAO getUserDAO() {
        return new UserDAO(this);
    }

}
