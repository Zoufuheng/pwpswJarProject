package com.pwpew.modeldriven;

import com.pwpew.entity.TVolunteer;

/**
 * @author Yani
 * @date 2020/5/24 - 9:43
 * @commet:
 */
public class VolunteerMd extends TVolunteer {

    // easyUi分页参数
    private int page;
    private int rows;

    //验证码
    private String verifyCode;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
