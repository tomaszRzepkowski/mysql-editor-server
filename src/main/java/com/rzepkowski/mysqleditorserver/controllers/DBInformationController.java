package com.rzepkowski.mysqleditorserver.controllers;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rzepkowski.mysqleditorserver.services.DBConnectionService;

@RestController
@RequestMapping("/info")
public class DBInformationController {

    private static final String SEMICOLON = ";";
    final DBConnectionService dbConnectionService;

    public DBInformationController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @GetMapping("/tables")
    Map<String, Object> getTables(@RequestParam String schemaName) {
        try {
            return dbConnectionService.executeStatement("select table_name\n" +
                    "from information_schema.tables\n" +
                    "where table_type = 'BASE TABLE' and table_schema = '" + schemaName + "'");
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/tables/columns")
    Map<String, Object> getColumns(@RequestParam String schemaName,
                                   @RequestParam String tableName) {
        try {
            return dbConnectionService.executeStatement("SHOW COLUMNS FROM " + schemaName + "." + tableName + SEMICOLON);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/tables/triggers")
    Map<String, Object> getTriggers(@RequestParam String schemaName) {
        try {
            return dbConnectionService.executeStatement("SHOW TRIGGERS FROM " + schemaName + SEMICOLON);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/tables/indexes")
    Map<String, Object> getIndexes(@RequestParam String schemaName,
                                    @RequestParam String tableName) {
        try {
            return dbConnectionService.executeStatement("SHOW INDEX FROM " + schemaName + "." + tableName + SEMICOLON);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/schemas")
    Map<String, Object> getSchemas() {
        try {
            return dbConnectionService.executeStatement("show databases;");
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

    @GetMapping("/schemas/views") // todo fixme bo to nie bardzo dziala
    Map<String, Object> getViews(@RequestParam String schemaName) {
        try {
            return dbConnectionService.executeStatement("SHOW FULL TABLES IN " + schemaName + " WHERE TABLE_TYPE LIKE 'VIEW';\n");
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/schemas/procedures")
    Map<String, Object> getProcedures(@RequestParam String schemaName) {
        try {
            return dbConnectionService.executeStatement("SHOW PROCEDURE STATUS WHERE db = '" + schemaName + "'" + SEMICOLON);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/schemas/functions")
    Map<String, Object> getFunctions(@RequestParam String schemaName) {
        try {
            return dbConnectionService.executeStatement("SHOW FUNCTION STATUS WHERE db = '" + schemaName + "'" + SEMICOLON);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping(value = "/schema", produces = "text/plain")
    String getSelectedSchema() {
        return dbConnectionService.getDataSourceProvider().getCurrentSchema();
    }

    @PutMapping("/schema")
    void setSchema(@RequestParam String schemaName) {
        dbConnectionService.selectSchema(schemaName);
    }
}
