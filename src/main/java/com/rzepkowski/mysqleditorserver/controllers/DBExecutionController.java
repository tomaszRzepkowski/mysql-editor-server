package com.rzepkowski.mysqleditorserver.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rzepkowski.mysqleditorserver.model.ActionResponse;
import com.rzepkowski.mysqleditorserver.model.SimplePair;
import com.rzepkowski.mysqleditorserver.model.TableAction;
import com.rzepkowski.mysqleditorserver.services.DBConnectionService;
import com.rzepkowski.mysqleditorserver.shared.SqlActionsBuilder;
import com.rzepkowski.mysqleditorserver.shared.SqlInsertBuilder;

@RestController
@RequestMapping("/execute")
public class DBExecutionController {

    final DBConnectionService dbConnectionService;

    public DBExecutionController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @GetMapping
    Map<String, Object> runSql(@RequestParam String sql) {
        try {
            return dbConnectionService.executeStatement(sql);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @PostMapping("/insert")
    void executeInsert(@RequestBody List<SimplePair> pairs) {
        // todo pass here schema and table
        String schemaName = "test_schema";
        String tableName = "users";
        SqlInsertBuilder insertBuilder = new SqlInsertBuilder();
        String sql = insertBuilder.withSchema(schemaName).withTable(tableName).withPairs(pairs).build();
        try {
            dbConnectionService.executeInsert(sql);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/actions")
    ActionResponse executeAction(@RequestParam String schema,
                                 @RequestParam String table,
                                 @RequestParam(required = false) Integer limit,
                                 @RequestParam(required = false) String orderBy,
                                 @RequestParam TableAction action) {
        SqlActionsBuilder builder = new SqlActionsBuilder();
        String sql = builder
                .getAction(action)
                .fromSchemaAndTable(schema, table)
                .orderBy(orderBy)
                .limit(limit)
                .build();
        try {
            Map<String, Object> columnsAndRows = dbConnectionService.executeStatement(sql);
            ActionResponse response = new ActionResponse();
            response.setColumnsAndRows(columnsAndRows);
            response.setSql(sql);
            return response;
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }
}
