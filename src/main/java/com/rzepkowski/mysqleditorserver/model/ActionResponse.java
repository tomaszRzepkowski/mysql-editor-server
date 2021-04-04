package com.rzepkowski.mysqleditorserver.model;

import java.util.Map;

public class ActionResponse {
    private Map<String, Object> columnsAndRows;
    private String sql;

    public Map<String, Object> getColumnsAndRows() {
        return columnsAndRows;
    }

    public void setColumnsAndRows(Map<String, Object> columnsAndRows) {
        this.columnsAndRows = columnsAndRows;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
