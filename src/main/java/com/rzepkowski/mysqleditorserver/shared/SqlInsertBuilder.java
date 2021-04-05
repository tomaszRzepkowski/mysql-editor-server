package com.rzepkowski.mysqleditorserver.shared;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.rzepkowski.mysqleditorserver.model.SimplePair;
import com.rzepkowski.mysqleditorserver.model.TableAction;

public class SqlInsertBuilder {

    private StringBuilder builder = new StringBuilder();
    private String schemaName;
    private String tableName;
//    String schemaName = "test_schema";
//    String tableName = "users";

    public SqlInsertBuilder withSchema(String schemaName) {
        this.schemaName = schemaName;
        return this;
    }

    public SqlInsertBuilder withTable(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SqlInsertBuilder withPairs(List<SimplePair> pairs) {
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        pairs.stream().filter(pair -> StringUtils.isNotEmpty(pair.getValue())).forEach(pair -> {
            keys.append(pair.getKey()).append(',');
            values.append('\'').append(pair.getValue()).append('\'').append(',');
        });
        // remove last comma
        keys.deleteCharAt(keys.length() - 1);
        values.deleteCharAt(values.length() - 1);

        builder.append("INSERT INTO ").append(schemaName).append('.').append(tableName).append(StringUtils.SPACE)
                .append('(').append(keys.toString()).append(')').append(StringUtils.SPACE)
                .append("VALUES ")
                .append('(').append(values.toString()).append(')').append(';');
        return this;
    }

    public String build() {
        if (StringUtils.isEmpty(schemaName)) {
            throw new NullPointerException("Schema name is missing");
        }
        if (StringUtils.isEmpty(tableName)) {
            throw new NullPointerException("Table name is missing");
        }
        return builder.toString();
    }
}
