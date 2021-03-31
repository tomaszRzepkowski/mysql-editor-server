package com.rzepkowski.mysqleditorserver.user;

public class DBUserSqlBuilder {
    public static String buildQuery(DBUserData data) {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE mysql.user SET ")
                .append("host = '" + data.getHost() + "',")
                .append("user = '" + data.getUsername() + "',")
                .append("password_last_changed = '" + data.getLastPasswordChange() + "',")
                .append("account_locked = '" + data.getAccountLocked() + "',")
                .append("select_priv = '" + data.getSelectPrivilege() + "',")
                .append("insert_priv = '" + data.getInsertPrivilege() + "',")
                .append("Update_priv = '" + data.getUpdatePrivilege() + "',")
                .append("Delete_priv = '" + data.getDeletePrivilege() + "',")
                .append("Create_priv = '" + data.getCreatePrivilege() + "',")
                .append("Drop_priv = '" + data.getDropPrivilege() + "',")
                .append("Reload_priv = '" + data.getReloadPrivilege() + "',")
                .append("Shutdown_priv = '" + data.getShutdownPrivilege() + "',")
                .append("Grant_priv = '" + data.getGrantPrivilege() + "',")
                .append("Index_priv = '" + data.getIndexPrivilege() + "',")
                .append("Alter_priv = '" + data.getAlterPrivilege() + "',")
                .append("Show_db_priv = '" + data.getShowDbPrivilege() + "',")
                .append("Execute_priv = '" + data.getExecutePrivilege() + "',")
                .append("Trigger_priv = '" + data.getTriggerPrivilege() + "',")
                .append("Drop_role_priv = '" + data.getDropRolePrivilege() + "',")
                .append("Create_tablespace_priv = '" + data.getCreateTablespacePrivilege() + "',")
                .append("Create_role_priv = '" + data.getCreateRolePrivilege() + "',")
                .append("Create_user_priv = '" + data.getCreateUserPrivilege() + "',")
                .append("Event_priv = '" + data.getEventPrivilege() + "'")
                .append("WHERE user = '" + data.getUsername() + "'");
        return builder.toString();
    }

    public static String buildQueryForCreate(DBUserData data) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE USER '" +
                data.getUsername() +
                "'@'" +
                data.getHost() +
                "' IDENTIFIED BY '" +
                data.getPassword() +
                "';");
        return builder.toString();
    }


    public static String buildQueryForInsert(DBUserData data) {
    StringBuilder builder = new StringBuilder();
    builder.append("'" + data.getHost() + "',")
            .append("'" + data.getUsername() + "',")
            .append("'" + data.getAccountLocked() + "',")
            .append("'" + data.getSelectPrivilege() + "',")
            .append("'" + data.getInsertPrivilege() + "',")
            .append("'" + data.getUpdatePrivilege() + "',")
            .append("'" + data.getDeletePrivilege() + "',")
            .append("'" + data.getCreatePrivilege() + "',")
            .append("'" + data.getDropPrivilege() + "',")
            .append("'" + data.getReloadPrivilege() + "',")
            .append("'" + data.getShutdownPrivilege() + "',")
            .append("'" + data.getGrantPrivilege() + "',")
            .append("'" + data.getIndexPrivilege() + "',")
            .append("'" + data.getAlterPrivilege() + "',")
            .append("'" + data.getShowDbPrivilege() + "',")
            .append("'" + data.getExecutePrivilege() + "',")
            .append("'" + data.getTriggerPrivilege() + "',")
            .append("'" + data.getDropRolePrivilege() + "',")
            .append("'" + data.getCreateTablespacePrivilege() + "',")
            .append("'" + data.getCreateRolePrivilege() + "',")
            .append("'" + data.getCreateUserPrivilege() + "',")
            .append("'" + data.getEventPrivilege() + "'");
    return "INSERT INTO mysql.user (" +
            "host,\n" +
            "user,\n" +
            "account_locked,\n" +
            "select_priv,\n" +
            "insert_priv,\n" +
            "Update_priv,\n" +
            "Delete_priv,\n" +
            "Create_priv,\n" +
            "Drop_priv,\n" +
            "Reload_priv,\n" +
            "Shutdown_priv,\n" +
            "Grant_priv,\n" +
            "Index_priv,\n" +
            "Alter_priv,\n" +
            "Show_db_priv,\n" +
            "Execute_priv,\n" +
            "Trigger_priv,\n" +
            "Drop_role_priv,\n" +
            "Create_tablespace_priv,\n" +
            "Create_role_priv,\n" +
            "Create_user_priv,\n" +
            "Event_priv" +
            ")\n" +
            "VALUES (" + builder.toString() + ");";
    }

    public static String buildQueryForDelete(String host, String username) {
        StringBuilder builder = new StringBuilder();
        builder.append("DROP USER '" + username + "'@'" + host + "';");
        return builder.toString();
    }
}
