package com.pwpew.modeldriven;

import com.pwpew.entity.TComment;

/**
 * @author miaoyin
 * @date 2020/5/21 - 14:01
 * @commet:
 */
public class CommentMd extends TComment {

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
