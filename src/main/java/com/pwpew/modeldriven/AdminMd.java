package com.pwpew.modeldriven;

import com.pwpew.entity.TAdministrator;

/**
 * @author miaoyin
 * @date 2020/5/13 - 12:58
 * @commet:
 */
public class AdminMd extends TAdministrator {

    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
