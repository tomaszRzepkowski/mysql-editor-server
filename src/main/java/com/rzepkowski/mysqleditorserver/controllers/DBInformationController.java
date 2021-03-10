package com.rzepkowski.mysqleditorserver.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rzepkowski.mysqleditorserver.services.DBConnectionService;

@RestController
@RequestMapping("/info")
public class DBInformationController {

    final DBConnectionService dbConnectionService;

    public DBInformationController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @GetMapping("/tables")
    Map<String, Object> getTables() {
        return dbConnectionService.executeStatement("select schema_name as database_name " +
                "from information_schema.schemata " +
                "order by schema_name;");
    }

    @GetMapping("/schemas")
    Map<String, Object> getSchemas() {
//        return dbConnectionService.executeStatement("show databases;");
        return dbConnectionService.executeStatement("select * " +
                "from information_schema.schemata " +
                "order by schema_name;");
    }

    @PutMapping("/schema")
    void setSchema(@RequestParam String schemaName) {
        dbConnectionService.selectSchema(schemaName);
    }
}
