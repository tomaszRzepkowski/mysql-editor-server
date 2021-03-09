package com.rzepkowski.mysqleditorserver;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.MysqlDataSource;

@Service
public class DataSourceProvider {
    private Logger logger =  LoggerFactory.getLogger(DataSourceProvider.class);

    private MysqlDataSource dataSource = new MysqlDataSource();
    private String dbName = "sys";
    private String dbName2 = "information_schema";
    private String dbHost = "localhost";
    private int dbPort = 3306;
    private String dbUser = "editor";
    private String dbPassword = "password";

    public DataSourceProvider() {
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setServerName(dbHost);
        dataSource.setPort(dbPort);
        dataSource.setDatabaseName(dbName2);
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() {
        try {
            getDataSource().getConnection();
        } catch (SQLException throwables) {
            logger.warn("Couldn't provide DB connection", throwables);
        }
        return null;
    }
}
