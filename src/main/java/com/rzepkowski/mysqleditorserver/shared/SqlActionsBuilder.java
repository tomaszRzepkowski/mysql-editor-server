package com.rzepkowski.mysqleditorserver.shared;

import org.apache.commons.lang3.StringUtils;

import com.rzepkowski.mysqleditorserver.model.TableAction;

public class SqlActionsBuilder {

    private StringBuilder builder = new StringBuilder();

    public SqlActionsBuilder getAction(TableAction action) {
        switch ( action ) {
            case SELECT:
                return this.asSelectAll();
            default:
                return this.asSelectAll();
        }
    }

    public SqlActionsBuilder asSelectAll() {
        builder.append("SELECT *").append(StringUtils.SPACE);
        return this;
    }

    public SqlActionsBuilder asSelect(String select) {
        if (select != null) {
            builder.append("SELECT ").append(select).append(StringUtils.SPACE);
        }
        return this;
    }

    public SqlActionsBuilder limit(Integer limit) {
        if (limit != null) {
            builder.append("LIMIT ").append(limit).append(StringUtils.SPACE);
        }
        return this;
    }

    public SqlActionsBuilder from(String table) {
        if (table != null) {
            builder.append("FROM ").append(table).append(StringUtils.SPACE);
        }
        return this;
    }

    public SqlActionsBuilder fromSchemaAndTable(String schema, String table) {
        if (schema == null || table == null) {
            return this;
        }
        builder.append("FROM ")
                .append(schema)
                .append(".")
                .append(table)
                .append(StringUtils.SPACE);
        return this;
    }

    public SqlActionsBuilder orderBy(String orderBy) {
        if (orderBy != null) {
            builder.append("ORDER BY ").append(orderBy).append(StringUtils.SPACE);
        }
        return this;
    }

    public SqlActionsBuilder groupBy(String groupBy) {
        if (groupBy != null) {
            builder.append("GROUP BY ").append(groupBy).append(StringUtils.SPACE);
        }
        return this;
    }

    public SqlActionsBuilder where(String where) {
        if (where != null) {
            builder.append("WHERE ").append(where).append(StringUtils.SPACE);
        }
        return this;
    }

    public String build() {
        builder.append(";");
        return builder.toString();
    }


}
