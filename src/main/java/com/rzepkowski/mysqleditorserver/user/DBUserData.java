package com.rzepkowski.mysqleditorserver.user;

import com.rzepkowski.mysqleditorserver.model.YNEnum;

public class DBUserData {
    String username;
    String host;
    String lastPasswordChange;
    YNEnum accountLocked;
    YNEnum selectPrivilege;
    YNEnum insertPrivilege;
    YNEnum updatePrivilege;
    YNEnum deletePrivilege;
    YNEnum createPrivilege;
    YNEnum dropPrivilege;
    YNEnum reloadPrivilege;
    YNEnum shutdownPrivilege;
    YNEnum grantPrivilege;
    YNEnum indexPrivilege;
    YNEnum alterPrivilege;
    YNEnum showDbPrivilege;
    YNEnum executePrivilege;
    YNEnum triggerPrivilege;
    YNEnum dropRolePrivilege;
    YNEnum createTablespacePrivilege;
    YNEnum createRolePrivilege;
    YNEnum createUserPrivilege;
    YNEnum eventPrivilege;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public YNEnum getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(YNEnum accountLocked) {
        this.accountLocked = accountLocked;
    }

    public YNEnum getSelectPrivilege() {
        return selectPrivilege;
    }

    public void setSelectPrivilege(YNEnum selectPrivilege) {
        this.selectPrivilege = selectPrivilege;
    }

    public YNEnum getInsertPrivilege() {
        return insertPrivilege;
    }

    public void setInsertPrivilege(YNEnum insertPrivilege) {
        this.insertPrivilege = insertPrivilege;
    }

    public YNEnum getUpdatePrivilege() {
        return updatePrivilege;
    }

    public void setUpdatePrivilege(YNEnum updatePrivilege) {
        this.updatePrivilege = updatePrivilege;
    }

    public YNEnum getDeletePrivilege() {
        return deletePrivilege;
    }

    public void setDeletePrivilege(YNEnum deletePrivilege) {
        this.deletePrivilege = deletePrivilege;
    }

    public YNEnum getCreatePrivilege() {
        return createPrivilege;
    }

    public void setCreatePrivilege(YNEnum createPrivilege) {
        this.createPrivilege = createPrivilege;
    }

    public YNEnum getDropPrivilege() {
        return dropPrivilege;
    }

    public void setDropPrivilege(YNEnum dropPrivilege) {
        this.dropPrivilege = dropPrivilege;
    }

    public YNEnum getReloadPrivilege() {
        return reloadPrivilege;
    }

    public void setReloadPrivilege(YNEnum reloadPrivilege) {
        this.reloadPrivilege = reloadPrivilege;
    }

    public YNEnum getShutdownPrivilege() {
        return shutdownPrivilege;
    }

    public void setShutdownPrivilege(YNEnum shutdownPrivilege) {
        this.shutdownPrivilege = shutdownPrivilege;
    }

    public YNEnum getGrantPrivilege() {
        return grantPrivilege;
    }

    public void setGrantPrivilege(YNEnum grantPrivilege) {
        this.grantPrivilege = grantPrivilege;
    }

    public YNEnum getIndexPrivilege() {
        return indexPrivilege;
    }

    public void setIndexPrivilege(YNEnum indexPrivilege) {
        this.indexPrivilege = indexPrivilege;
    }

    public YNEnum getAlterPrivilege() {
        return alterPrivilege;
    }

    public void setAlterPrivilege(YNEnum alterPrivilege) {
        this.alterPrivilege = alterPrivilege;
    }

    public YNEnum getShowDbPrivilege() {
        return showDbPrivilege;
    }

    public void setShowDbPrivilege(YNEnum showDbPrivilege) {
        this.showDbPrivilege = showDbPrivilege;
    }

    public YNEnum getExecutePrivilege() {
        return executePrivilege;
    }

    public void setExecutePrivilege(YNEnum executePrivilege) {
        this.executePrivilege = executePrivilege;
    }

    public YNEnum getTriggerPrivilege() {
        return triggerPrivilege;
    }

    public void setTriggerPrivilege(YNEnum triggerPrivilege) {
        this.triggerPrivilege = triggerPrivilege;
    }

    public YNEnum getDropRolePrivilege() {
        return dropRolePrivilege;
    }

    public void setDropRolePrivilege(YNEnum dropRolePrivilege) {
        this.dropRolePrivilege = dropRolePrivilege;
    }

    public YNEnum getCreateTablespacePrivilege() {
        return createTablespacePrivilege;
    }

    public void setCreateTablespacePrivilege(YNEnum createTablespacePrivilege) {
        this.createTablespacePrivilege = createTablespacePrivilege;
    }

    public YNEnum getCreateRolePrivilege() {
        return createRolePrivilege;
    }

    public void setCreateRolePrivilege(YNEnum createRolePrivilege) {
        this.createRolePrivilege = createRolePrivilege;
    }

    public YNEnum getCreateUserPrivilege() {
        return createUserPrivilege;
    }

    public void setCreateUserPrivilege(YNEnum createUserPrivilege) {
        this.createUserPrivilege = createUserPrivilege;
    }

    public YNEnum getEventPrivilege() {
        return eventPrivilege;
    }

    public void setEventPrivilege(YNEnum eventPrivilege) {
        this.eventPrivilege = eventPrivilege;
    }
}
