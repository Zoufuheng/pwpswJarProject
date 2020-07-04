package com.pwpew.modeldriven;

import com.pwpew.entity.TMessage;

import java.sql.Timestamp;

/**
 * @author miaoyin
 * @date 2020/5/16 - 13:24
 * @commet:
 */
public class MessageMd extends TMessage {
    // easyUi分页参数
    private int page;
    private int rows;

    private int msgIsRes;

    private Timestamp msgDate_end;


    public int getMsgIsRes() {
        return msgIsRes;
    }

    public void setMsgIsRes(int msgIsRes) {
        this.msgIsRes = msgIsRes;
    }



    public Timestamp getMsgDate_end() {
        return msgDate_end;
    }

    public void setMsgDate_end(Timestamp msgDate_end) {
        this.msgDate_end = msgDate_end;
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
