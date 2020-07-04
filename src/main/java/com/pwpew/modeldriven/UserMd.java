package com.pwpew.modeldriven;

import com.pwpew.entity.TUser;

import java.io.File;

/**
 * @author miaoyin
 * @date 2020/5/8 - 9:43
 * @commet:
 */

//继承了Tuser，相当于Tuser的加强版,便于扩展
public class UserMd extends TUser {

    //验证码
    private String verifyCode;

    //忘记密码的验证码
    private String randomNumber;

    //新密码
    private String newPassword;

    // easyUi分页参数
    private int page;
    private int rows;

    //客户端上传的图片，只要此属性有值说明客户端上传图片成功
    private File picture;
    //获取原始文件名称,命名规则：页面上file的名称+FileName
    private String pictureFileName;
    //获取原始文件类型，命名规则：页面上file的名称+ContentType
    private String pictureContentType;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

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

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureContentType() {
        return pictureContentType;
    }

    public void setPictureContentType(String pictureContentType) {
        this.pictureContentType = pictureContentType;
    }


    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
