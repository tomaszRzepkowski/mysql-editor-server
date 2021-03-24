package com.rzepkowski.mysqleditorserver.controllers;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rzepkowski.mysqleditorserver.user.DBUserData;
import com.rzepkowski.mysqleditorserver.model.YNEnum;
import com.rzepkowski.mysqleditorserver.services.DBConnectionService;

@RestController
@RequestMapping("/users")
public class DBUsersController {
    final DBConnectionService dbConnectionService;

    public DBUsersController(DBConnectionService dbConnectionService) {
        this.dbConnectionService = dbConnectionService;
    }

    @GetMapping()
    Map<String, Object> getUsers() {
        try {
            return dbConnectionService.executeStatement("SELECT host, user, password_last_changed, account_locked from mysql.user;");
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

    @GetMapping("/info")
    DBUserData getUsersWithName(@RequestParam String username) {
        try {
            Map<String, Object> userData = dbConnectionService.executeSingleStatement("" +
                    "SELECT host as HOST, user as USER, password_last_changed as LAST_PASSWORD_CHANGE, account_locked as ACCOUNT_LOCKED,\n" +
                    "       select_priv as SELECT_PRIVILEGE, \n" +
                    "       insert_priv as INSERT_PRIVILEGE,\n" +
                    "       Update_priv as UPDATE_PRIVILEGE,\n" +
                    "       Delete_priv as DELETE_PRIVILEGE, \n" +
                    "       Create_priv as CREATE_PRIVILEGE, \n" +
                    "       Drop_priv as DROP_PRIVILEGE, \n" +
                    "       Reload_priv as RELOAD_PRIVILEGE, \n" +
                    "       Shutdown_priv as SHUTDOWN_PRIVILEGE,\n" +
                    "       Grant_priv as GRANT_PRIVILEGE,\n" +
                    "       Index_priv as INDEX_PRIVILEGE,\n" +
                    "       Alter_priv as ALTER_PRIVILEGE,\n" +
                    "       Show_db_priv as SHOW_DB_PRIVILEGE,\n" +
                    "       Execute_priv as EXECUTE_PRIVILEGE,\n" +
                    "       Trigger_priv as TRIGGER_PRIVILEGE,\n" +
                    "       Drop_role_priv as DROP_ROLE_PRIVILEGE,\n" +
                    "       Create_tablespace_priv as CREATE_TABLESPACE_PRIVILEGE,\n" +
                    "       Create_role_priv as CREATE_ROLE_PRIVILEGE,\n" +
                    "       Create_user_priv as CREATE_USER_PRIVILEGE,\n" +
                    "       Event_priv as EVENT_PRIVILEGE\n" +
                    "from mysql.user where " +
                    "user = '" + username + "';", true);
            DBUserData dbUserData = new DBUserData();
            dbUserData.setUsername(String.valueOf(userData.get("USER")));
            dbUserData.setHost(String.valueOf(userData.get("HOST")));
            dbUserData.setLastPasswordChange(String.valueOf(userData.get("LAST_PASSWORD_CHANGE")));
            dbUserData.setAccountLocked(YNEnum.valueFrom(userData.get("ACCOUNT_LOCKED")));
            dbUserData.setSelectPrivilege(YNEnum.valueFrom(userData.get("SELECT_PRIVILEGE")));
            dbUserData.setInsertPrivilege(YNEnum.valueFrom(userData.get("INSERT_PRIVILEGE")));
            dbUserData.setUpdatePrivilege(YNEnum.valueFrom(userData.get("UPDATE_PRIVILEGE")));
            dbUserData.setDeletePrivilege(YNEnum.valueFrom(userData.get("DELETE_PRIVILEGE")));
            dbUserData.setCreatePrivilege(YNEnum.valueFrom(userData.get("CREATE_PRIVILEGE")));
            dbUserData.setDropPrivilege(YNEnum.valueFrom(userData.get("DROP_PRIVILEGE")));
            dbUserData.setReloadPrivilege(YNEnum.valueFrom(userData.get("RELOAD_PRIVILEGE")));
            dbUserData.setShutdownPrivilege(YNEnum.valueFrom(userData.get("SHUTDOWN_PRIVILEGE")));
            dbUserData.setGrantPrivilege(YNEnum.valueFrom(userData.get("GRANT_PRIVILEGE")));
            dbUserData.setIndexPrivilege(YNEnum.valueFrom(userData.get("INDEX_PRIVILEGE")));
            dbUserData.setAlterPrivilege(YNEnum.valueFrom(userData.get("ALTER_PRIVILEGE")));
            dbUserData.setShowDbPrivilege(YNEnum.valueFrom(userData.get("SHOW_DB_PRIVILEGE")));
            dbUserData.setExecutePrivilege(YNEnum.valueFrom(userData.get("EXECUTE_PRIVILEGE")));
            dbUserData.setTriggerPrivilege(YNEnum.valueFrom(userData.get("TRIGGER_PRIVILEGE")));
            dbUserData.setDropRolePrivilege(YNEnum.valueFrom(userData.get("DROP_ROLE_PRIVILEGE")));
            dbUserData.setCreateTablespacePrivilege(YNEnum.valueFrom(userData.get("CREATE_TABLESPACE_PRIVILEGE")));
            dbUserData.setCreateRolePrivilege(YNEnum.valueFrom(userData.get("CREATE_ROLE_PRIVILEGE")));
            dbUserData.setCreateUserPrivilege(YNEnum.valueFrom(userData.get("CREATE_USER_PRIVILEGE")));
            dbUserData.setEventPrivilege(YNEnum.valueFrom(userData.get("EVENT_PRIVILEGE")));
            return dbUserData;
        } catch (SQLException throwables) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, throwables.getMessage(), throwables);
        }
    }

}

//       Process_priv, File_priv, References_priv, Index_priv, Alter_priv, Show_db_priv, Super_priv, Create_tmp_table_priv, Lock_tables_priv, Execute_priv, Repl_slave_priv, Repl_client_priv, Create_view_priv, Show_view_priv, Create_routine_priv, Alter_routine_priv, Create_user_priv, Event_priv, Trigger_priv, Create_tablespace_priv, ssl_type, ssl_cipher, x509_issuer, x509_subject, max_questions, max_updates, max_connections, max_user_connections, plugin, authentication_string, password_expired, password_last_changed, password_lifetime, account_locked, Create_role_priv, Drop_role_priv, Password_reuse_history, Password_reuse_time, Password_require_current, User_attributes
