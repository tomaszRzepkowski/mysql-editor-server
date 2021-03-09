package com.rzepkowski.mysqleditorserver.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rzepkowski.mysqleditorserver.services.DBConnectionService;

@RestController
@RequestMapping("/info")
public class DBInformationController {

    final DBConnectionService dbConnectionService;

    public DBInformationController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @GetMapping
    void test() {
        try {
            dbConnectionService.tryToConnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
