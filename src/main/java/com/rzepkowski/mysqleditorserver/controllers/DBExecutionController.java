package com.rzepkowski.mysqleditorserver.controllers;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rzepkowski.mysqleditorserver.services.DBConnectionService;

@RestController
@RequestMapping("/execute")
public class DBExecutionController {

    final DBConnectionService dbConnectionService;

    public DBExecutionController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @GetMapping
    Map<String, Object> runSql(@RequestParam String sql) {
        System.out.println(sql);
        try {
            return dbConnectionService.executeStatement(sql);
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

}
