package com.pwpew.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author miaoyin
 * @date 2020/5/13 - 10:36
 * @commet:
 */
public class TNotice {
    private int ntsId;
    private String ntsMain;
    private String ntsHeadLine;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp ntsDate;

    private TAdministrator administrator;

    public TAdministrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(TAdministrator administrator) {
        this.administrator = administrator;
    }

    public int getNtsId() {
        return ntsId;
    }

    public void setNtsId(int ntsId) {
        this.ntsId = ntsId;
    }

    public String getNtsMain() {
        return ntsMain;
    }

    public void setNtsMain(String ntsMain) {
        this.ntsMain = ntsMain;
    }

    public String getNtsHeadLine() {
        return ntsHeadLine;
    }

    public void setNtsHeadLine(String ntsHeadLine) {
        this.ntsHeadLine = ntsHeadLine;
    }

    public Timestamp getNtsDate() {
        return ntsDate;
    }

    public void setNtsDate(Timestamp ntsDate) {
        this.ntsDate = ntsDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TNotice tNotice = (TNotice) o;
        return ntsId == tNotice.ntsId &&
                Objects.equals(ntsMain, tNotice.ntsMain) &&
                Objects.equals(ntsHeadLine, tNotice.ntsHeadLine) &&
                Objects.equals(ntsDate, tNotice.ntsDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ntsId, ntsMain, ntsHeadLine, ntsDate);
    }
}
