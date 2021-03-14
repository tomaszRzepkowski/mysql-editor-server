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

    @GetMapping(value = "/schema", produces = "text/plain")
    String getSelectedSchema() {
        return dbConnectionService.getDataSourceProvider().getCurrentSchema();
    }

    @PutMapping("/schema")
    void setSchema(@RequestParam String schemaName) {
        dbConnectionService.selectSchema(schemaName);
    }
}
