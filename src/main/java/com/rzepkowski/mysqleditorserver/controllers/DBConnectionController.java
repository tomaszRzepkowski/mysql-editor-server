package com.rzepkowski.mysqleditorserver.controllers;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rzepkowski.mysqleditorserver.model.ConnectionModel;
import com.rzepkowski.mysqleditorserver.services.DBConnectionService;

@RestController
@RequestMapping("/connect")
public class DBConnectionController {
    final DBConnectionService dbConnectionService;

    public DBConnectionController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @PostMapping
    void runSql(@RequestBody ConnectionModel data) {
        try {
            boolean connectionEstablished = dbConnectionService.tryToEstablishConnection(data);
            if (!connectionEstablished) {
                throw new SQLException("Connection closed");
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
