package com.pwpew.entity;

import java.util.Objects;

public class TVolunteer {
    private int volId;
    private String volname;
    private String volIdCard;
    private String volPhone;
    private String volSex;
    private String volProvince;
    private String volCity;
    private String voldetailAddr;
    private String volProfession;
    private String volEmail;
    private String volQq;

    public int getVolId() {
        return volId;
    }

    public void setVolId(int volId) {
        this.volId = volId;
    }

    public String getVolname() {
        return volname;
    }

    public void setVolname(String volname) {
        this.volname = volname;
    }

    public String getVolIdCard() {
        return volIdCard;
    }

    public void setVolIdCard(String volIdCard) {
        this.volIdCard = volIdCard;
    }

    public String getVolPhone() {
        return volPhone;
    }

    public void setVolPhone(String volPhone) {
        this.volPhone = volPhone;
    }

    public String getVolSex() {
        return volSex;
    }

    public void setVolSex(String volSex) {
        this.volSex = volSex;
    }

    public String getVolProvince() {
        return volProvince;
    }

    public void setVolProvince(String volProvince) {
        this.volProvince = volProvince;
    }

    public String getVolCity() {
        return volCity;
    }

    public void setVolCity(String volCity) {
        this.volCity = volCity;
    }

    public String getVoldetailAddr() {
        return voldetailAddr;
    }

    public void setVoldetailAddr(String voldetailAddr) {
        this.voldetailAddr = voldetailAddr;
    }

    public String getVolProfession() {
        return volProfession;
    }

    public void setVolProfession(String volProfession) {
        this.volProfession = volProfession;
    }

    public String getVolEmail() {
        return volEmail;
    }

    public void setVolEmail(String volEmail) {
        this.volEmail = volEmail;
    }

    public String getVolQq() {
        return volQq;
    }

    public void setVolQq(String volQq) {
        this.volQq = volQq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVolunteer that = (TVolunteer) o;
        return volId == that.volId &&
                Objects.equals(volname, that.volname) &&
                Objects.equals(volIdCard, that.volIdCard) &&
                Objects.equals(volPhone, that.volPhone) &&
                Objects.equals(volSex, that.volSex) &&
                Objects.equals(volProvince, that.volProvince) &&
                Objects.equals(volCity, that.volCity) &&
                Objects.equals(voldetailAddr, that.voldetailAddr) &&
                Objects.equals(volProfession, that.volProfession) &&
                Objects.equals(volEmail, that.volEmail) &&
                Objects.equals(volQq, that.volQq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volId, volname, volIdCard, volPhone, volSex, volProvince, volCity, voldetailAddr, volProfession, volEmail, volQq);
    }
}
