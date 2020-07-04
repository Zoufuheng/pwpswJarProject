package com.pwpew.modeldriven;

import com.alibaba.fastjson.annotation.JSONField;
import com.pwpew.entity.TPost;

import java.io.File;
import java.sql.Timestamp;

/**
 * @author miaoyin
 * @date 2020/5/21 - 14:00
 * @commet:
 */
public class PostMd extends TPost {

    // easyUi分页参数
    private int page;
    private int rows;
    private int totalPage;
    private int count;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //扩展的查询条件，起始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp postDate;

    //结束时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp postDate_end;

    //客户端上传的图片，只要此属性有值说明客户端上传图片成功
    private File picture;
    //获取原始文件名称,命名规则：页面上file的名称+FileName
    private String pictureFileName;
    //获取原始文件类型，命名规则：页面上file的名称+ContentType
    private String pictureContentType;

    private CommentMd commentMd;

    public CommentMd getCommentMd() {
        return commentMd;
    }

    public void setCommentMd(CommentMd commentMd) {
        this.commentMd = commentMd;
    }


    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Timestamp getPostDate_end() {
        return postDate_end;
    }

    public void setPostDate_end(Timestamp postDate_end) {
        this.postDate_end = postDate_end;
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
}
