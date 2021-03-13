package com.rzepkowski.mysqleditorserver;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.rzepkowski.mysqleditorserver.model.ConnectionModel;

@Service
public class DataSourceProvider {
    private final Logger logger =  LoggerFactory.getLogger(DataSourceProvider.class);

    private MysqlDataSource dataSource;
    private final static String dbName = "sys";
    private final static String INITIAL_SCHEMA = "information_schema";
    private final static String dbHost = "localhost";
    private final static int dbPort = 3306;
    private final static String dbUser = "editor";
    private final static String dbPassword = "password";

    private String currentSchema = INITIAL_SCHEMA;

    public DataSourceProvider() {
        dataSource = new MysqlDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setServerName(dbHost);
        dataSource.setPort(dbPort);
        dataSource.setDatabaseName(INITIAL_SCHEMA);
    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException throwables) {
            logger.warn("Couldn't provide DB connection", throwables);
        }
        return null;
    }

    public void selectSchema(String schemaName) {
        dataSource.setDatabaseName(schemaName);
        currentSchema = schemaName;
    }

    public String getCurrentSchema() {
        return currentSchema;
    }

    public Connection tryToConnect(ConnectionModel data) {
        dataSource = new MysqlDataSource();
        dataSource.setUser(data.getUsername());
        dataSource.setPassword(data.getPassword());
        dataSource.setServerName(data.getHost());
        dataSource.setPort(data.getPort());
        dataSource.setDatabaseName(data.getSchema());
        return getConnection();
    }
}
