package com.rzepkowski.mysqleditorserver.services;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rzepkowski.mysqleditorserver.DataSourceProvider;
import com.rzepkowski.mysqleditorserver.model.ConnectionModel;

@Service
public class DBConnectionService {
    private final Logger logger =  LoggerFactory.getLogger(DBConnectionService.class);

    final DataSourceProvider dataSourceProvider;

    public DBConnectionService(DataSourceProvider dataSourceProvider) {
        this.dataSourceProvider = dataSourceProvider;
    }

    /**
     * Returns multiple row from DB as Map
     */
    public Map<String, Object> executeStatement(String sql) throws SQLException {
        Map<String, Object> results = null;
        try {
            Statement stmt = dataSourceProvider.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            final ResultSetMetaData meta = rs.getMetaData();
            final int columnCount = meta.getColumnCount();
            final List<List<String>> rowList = new LinkedList<List<String>>();
            final List<String> columnNames = new ArrayList<>();
            while (rs.next())
            {
                final List<String> columnList = new LinkedList<String>();
                rowList.add(columnList);

                for (int column = 1; column <= columnCount; ++column)
                {
                    String columnName = rs.getMetaData().getColumnName(column);
                    if (columnNames.size() != columnCount) {
                        columnNames.add(columnName);
                    }

                    final Object value = rs.getObject(column);
                    columnList.add(String.valueOf(value));
                }
            }
            results = new HashMap<>();
            results.put("columns", columnNames);
            results.put("rows", rowList);
//            int columnCounter = 1;
//            int maxColumnCounter = rs.getMetaData().getColumnCount();
//            int rowCounter = 1;
//            while(rs.next()) {
//                String columnName = rs.getMetaData().getColumnName(columnCounter);
//                Object columnValue = rs.getObject(columnCounter);
//                System.out.println(columnValue);
//                results.put(columnName, columnValue);
//                rowCounter++;
//                if (columnCounter < maxColumnCounter) {
//                    columnCounter ++;
//                }
//            }
            rs.close();
            stmt.close();
            return results;
        } catch (SQLException e) {
            logger.warn("User provided wrong SQL or there is DB connection problem", e);
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Returns single row from DB as Map
     */
    public Map<String, Object> executeSingleStatement(String sql, boolean useLabel) throws SQLException {
        Map<String, Object> results = null;
        try {
            Statement stmt = dataSourceProvider.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            final ResultSetMetaData meta = rs.getMetaData();
            final int columnCount = meta.getColumnCount();
            while (rs.next())
            {
                results = new HashMap<>();
                for (int column = 1; column <= columnCount; ++column)
                {
                    String columnName = useLabel ? rs.getMetaData().getColumnLabel(column) : rs.getMetaData().getColumnName(column);
                    final Object value = rs.getObject(column);
                    results.put(columnName, value);
                }
            }
            rs.close();
            stmt.close();
            return results;
        } catch (SQLException e) {
            logger.warn("User provided wrong SQL or there is DB connection problem", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void selectSchema(String schemaName) {
        this.dataSourceProvider.selectSchema(schemaName);
    }

    public DataSourceProvider getDataSourceProvider() {
        return dataSourceProvider;
    }

    public boolean tryToEstablishConnection(ConnectionModel data) throws SQLException {
        Connection connection = this.dataSourceProvider.createNewConnection(data);
        return connection != null && !connection.isClosed();
    }

    public void executeUpdate(String query) throws SQLException {
        try {
            Statement stmt = dataSourceProvider.getConnection().createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            logger.warn("User provided wrong SQL or there is DB connection problem", e);
            throw new SQLException(e.getMessage());
        }
    }

    public void executeInsert(String query) throws SQLException {
        executeUpdate(query);
    }
}
