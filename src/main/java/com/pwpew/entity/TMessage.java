package com.pwpew.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.zip.ZipEntry;

/**
 * @author miaoyin
 * @date 2020/5/16 - 13:12
 * @commet:
 */
public class TMessage {
    private int msgId;
    private String msgMain;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp msgDate;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Timestamp msgResTime;

    private String msgResMain;
    //一个留言板只能由一个用户使用,一个用户可以发布多条留言
    //1对多的关系
    private TUser user;

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getMsgMain() {
        return msgMain;
    }

    public void setMsgMain(String msgMain) {
        this.msgMain = msgMain;
    }

    public Timestamp getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Timestamp msgDate) {
        this.msgDate = msgDate;
    }

    public Timestamp getMsgResTime() {
        return msgResTime;
    }

    public void setMsgResTime(Timestamp msgResTime) {
        this.msgResTime = msgResTime;
    }

    public String getMsgResMain() {
        return msgResMain;
    }

    public void setMsgResMain(String msgResMain) {
        this.msgResMain = msgResMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMessage tMessage = (TMessage) o;
        return msgId == tMessage.msgId &&
                Objects.equals(msgMain, tMessage.msgMain) &&
                Objects.equals(msgDate, tMessage.msgDate) &&
                Objects.equals(msgResTime, tMessage.msgResTime) &&
                Objects.equals(msgResMain, tMessage.msgResMain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgId, msgMain, msgDate, msgResTime, msgResMain);
    }
}
