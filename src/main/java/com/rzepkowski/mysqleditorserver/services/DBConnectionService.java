package com.rzepkowski.mysqleditorserver.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.MysqlDataSource;

@Service
public class DBConnectionService {

    String dbName = "sys";
    String dbName2 = "information_schema";
    String dbHost = "localhost";
    int dbPort = 3306;
    String dbUser = "editor";
    String dbPassword = "password";

    public void tryToConnect() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setServerName(dbHost);
        dataSource.setPort(dbPort);
        dataSource.setDatabaseName(dbName2);
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select schema_name as database_name\n" +
                "from information_schema.schemata\n" +
                "order by schema_name;");
        while(rs.next()) {
            String schema_name = rs.getString(1);
            System.out.println(schema_name);
        }


        rs.close();
        stmt.close();
        conn.close();
    }

}
