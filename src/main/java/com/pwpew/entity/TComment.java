package com.pwpew.entity;


import java.util.Date;
import java.util.Objects;

/**
 * @author miaoyin
 * @date 2020/5/21 - 13:47
 * @commet:
 */
public class TComment {
    private int commentId;
    private String comments;
    private Date commentTime;
    private int postId;

    //一个用户可以有多个评论,一个评论只能由一个用户创建
    private TUser user;

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TComment tComment = (TComment) o;
        return commentId == tComment.commentId &&
                Objects.equals(comments, tComment.comments) &&
                Objects.equals(commentTime, tComment.commentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comments, commentTime);
    }
}
