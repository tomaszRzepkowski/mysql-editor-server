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
    private final static String INITIAL_SCHEMA = "information_schema";
    private final static String DB_HOST = "localhost";
    private final static String DB_USER = "editor";
    private final static String DB_PASSWORD = "password";
    private final static int DB_PORT = 3306;
    private final Logger logger =  LoggerFactory.getLogger(DataSourceProvider.class);

    private MysqlDataSource dataSource;

    private String currentSchema = INITIAL_SCHEMA;

    public DataSourceProvider() {
//        dataSource = new MysqlDataSource();
//        dataSource.setUser(DB_USER);
//        dataSource.setPassword(DB_PASSWORD);
//        dataSource.setServerName(DB_HOST);
//        dataSource.setPort(DB_PORT);
//        dataSource.setDatabaseName(INITIAL_SCHEMA);
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

    public Connection createNewConnection(ConnectionModel data) {
        dataSource = new MysqlDataSource();
        dataSource.setUser(data.getUsername());
        dataSource.setPassword(data.getPassword());
        dataSource.setServerName(data.getHost());
        dataSource.setPort(data.getPort());
        dataSource.setDatabaseName(data.getSchema());
        return getConnection();
    }
}
