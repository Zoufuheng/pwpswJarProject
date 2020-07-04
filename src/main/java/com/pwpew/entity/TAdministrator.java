package com.pwpew.entity;

import java.util.Objects;

/**
 * @author miaoyin
 * @date 2020/5/13 - 11:40
 * @commet:
 */
public class TAdministrator {
    private int adminId;
    private String adminPassword;
    private String adminName;
    private String adminEmail;
    private String adminStatement;
    private String adminSex;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminStatement() {
        return adminStatement;
    }

    public void setAdminStatement(String adminStatement) {
        this.adminStatement = adminStatement;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TAdministrator that = (TAdministrator) o;
        return adminId == that.adminId &&
                Objects.equals(adminPassword, that.adminPassword) &&
                Objects.equals(adminName, that.adminName) &&
                Objects.equals(adminEmail, that.adminEmail) &&
                Objects.equals(adminStatement, that.adminStatement) &&
                Objects.equals(adminSex, that.adminSex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminPassword, adminName, adminEmail, adminStatement, adminSex);
    }
}
