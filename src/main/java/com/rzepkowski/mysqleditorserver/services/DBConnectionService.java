package com.rzepkowski.mysqleditorserver.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import com.rzepkowski.mysqleditorserver.DataSourceProvider;

@Service
public class DBConnectionService {
    final DataSourceProvider dataSourceProvider;

    public DBConnectionService(DataSourceProvider dataSourceProvider) {
        this.dataSourceProvider = dataSourceProvider;
    }

    public void tryToConnect() throws SQLException {
        Connection conn = dataSourceProvider.getConnection();
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
