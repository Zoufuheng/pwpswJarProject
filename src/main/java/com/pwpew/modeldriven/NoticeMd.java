package com.pwpew.modeldriven;

import com.pwpew.entity.TNotice;

import java.sql.Timestamp;

/**
 * @author miaoyin
 * @date 2020/5/13 - 9:54
 * @commet:
 */
public class NoticeMd extends TNotice {

    // easyUi分页参数
    private int page;
    private int rows;
    private Timestamp ntsDate_end;



    public Timestamp getNtsDate_end() {
        return ntsDate_end;
    }

    public void setNtsDate_end(Timestamp ntsDate_end) {
        this.ntsDate_end = ntsDate_end;
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
