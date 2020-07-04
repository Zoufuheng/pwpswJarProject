package com.pwpew.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author miaoyin
 * @date 2020/5/21 - 15:37
 * @commet:
 */
public class TPost {

    private int postId;
    private String postType;
    private String postName;
    private String postGender;
    private Integer postAge;
    //被寻人失联时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp missingtime;

    //发帖时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp postTime;

    private String postProvince;
    private String postCity;
    private String postCountry;
    private String postStreet;
    private String postDescribe;
    private String effectiveness;
    private String statue;
    private String postPicture;

    //一个用户可以发表多个帖子,一个帖子只能由一个用户发布
    private TUser user;

    //一个帖子有多个评论，一个评论只能发表在一个帖子上
    private Set<TComment> comments = new HashSet<TComment>();

    public Set<TComment> getComments() {
        return comments;
    }

    public void setComments(Set<TComment> comments) {
        this.comments = comments;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostGender() {
        return postGender;
    }

    public void setPostGender(String postGender) {
        this.postGender = postGender;
    }

    public Integer getPostAge() {
        return postAge;
    }

    public void setPostAge(Integer postAge) {
        this.postAge = postAge;
    }

    public Timestamp getMissingtime() {
        return missingtime;
    }

    public void setMissingtime(Timestamp missingtime) {
        this.missingtime = missingtime;
    }

    public String getPostProvince() {
        return postProvince;
    }

    public void setPostProvince(String postProvince) {
        this.postProvince = postProvince;
    }

    public String getPostCity() {
        return postCity;
    }

    public void setPostCity(String postCity) {
        this.postCity = postCity;
    }

    public String getPostCountry() {
        return postCountry;
    }

    public void setPostCountry(String postCountry) {
        this.postCountry = postCountry;
    }

    public String getPostStreet() {
        return postStreet;
    }

    public void setPostStreet(String postStreet) {
        this.postStreet = postStreet;
    }

    public String getPostDescribe() {
        return postDescribe;
    }

    public void setPostDescribe(String postDescribe) {
        this.postDescribe = postDescribe;
    }

    public String getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getPostPicture() {
        return postPicture;
    }

    public void setPostPicture(String postPicture) {
        this.postPicture = postPicture;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPost tPost = (TPost) o;

        if (postId != tPost.postId) return false;
        if (postType != null ? !postType.equals(tPost.postType) : tPost.postType != null) return false;
        if (postName != null ? !postName.equals(tPost.postName) : tPost.postName != null) return false;
        if (postGender != null ? !postGender.equals(tPost.postGender) : tPost.postGender != null) return false;
        if (postAge != null ? !postAge.equals(tPost.postAge) : tPost.postAge != null) return false;
        if (missingtime != null ? !missingtime.equals(tPost.missingtime) : tPost.missingtime != null) return false;
        if (postTime != null ? !postTime.equals(tPost.postTime) : tPost.postTime != null) return false;
        if (postProvince != null ? !postProvince.equals(tPost.postProvince) : tPost.postProvince != null) return false;
        if (postCity != null ? !postCity.equals(tPost.postCity) : tPost.postCity != null) return false;
        if (postCountry != null ? !postCountry.equals(tPost.postCountry) : tPost.postCountry != null) return false;
        if (postStreet != null ? !postStreet.equals(tPost.postStreet) : tPost.postStreet != null) return false;
        if (postDescribe != null ? !postDescribe.equals(tPost.postDescribe) : tPost.postDescribe != null) return false;
        if (effectiveness != null ? !effectiveness.equals(tPost.effectiveness) : tPost.effectiveness != null)
            return false;
        if (statue != null ? !statue.equals(tPost.statue) : tPost.statue != null) return false;
        if (postPicture != null ? !postPicture.equals(tPost.postPicture) : tPost.postPicture != null) return false;
        if (user != null ? !user.equals(tPost.user) : tPost.user != null) return false;
        return comments != null ? comments.equals(tPost.comments) : tPost.comments == null;
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + (postType != null ? postType.hashCode() : 0);
        result = 31 * result + (postName != null ? postName.hashCode() : 0);
        result = 31 * result + (postGender != null ? postGender.hashCode() : 0);
        result = 31 * result + (postAge != null ? postAge.hashCode() : 0);
        result = 31 * result + (missingtime != null ? missingtime.hashCode() : 0);
        result = 31 * result + (postTime != null ? postTime.hashCode() : 0);
        result = 31 * result + (postProvince != null ? postProvince.hashCode() : 0);
        result = 31 * result + (postCity != null ? postCity.hashCode() : 0);
        result = 31 * result + (postCountry != null ? postCountry.hashCode() : 0);
        result = 31 * result + (postStreet != null ? postStreet.hashCode() : 0);
        result = 31 * result + (postDescribe != null ? postDescribe.hashCode() : 0);
        result = 31 * result + (effectiveness != null ? effectiveness.hashCode() : 0);
        result = 31 * result + (statue != null ? statue.hashCode() : 0);
        result = 31 * result + (postPicture != null ? postPicture.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
