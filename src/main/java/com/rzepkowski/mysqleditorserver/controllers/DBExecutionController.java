package com.rzepkowski.mysqleditorserver.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return dbConnectionService.executeStatement(sql);
    }

}
