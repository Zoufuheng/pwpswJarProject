package com.pwpew.entity;

import java.util.Objects;

/**
 * @author miaoyin
 * @date 2020/5/8 - 10:37
 * @commet:
 */
public class TUser {
    private int userId;
    private String username;
    private String userPassword;
    private String userGender;
    private int userAge;
    private String userProvince;
    private String userCity;
    private String userPhone;
    private String email;
    private String userPicture;
    private String userNickname;

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUser tUser = (TUser) o;
        return userId == tUser.userId &&
                userAge == tUser.userAge &&
                Objects.equals(username, tUser.username) &&
                Objects.equals(userPassword, tUser.userPassword) &&
                Objects.equals(userGender, tUser.userGender) &&
                Objects.equals(userProvince, tUser.userProvince) &&
                Objects.equals(userCity, tUser.userCity) &&
                Objects.equals(userPhone, tUser.userPhone) &&
                Objects.equals(email, tUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userPassword, userGender, userAge, userProvince, userCity, userPhone, email);
    }
}
