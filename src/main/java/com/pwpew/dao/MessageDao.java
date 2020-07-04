package com.pwpew.dao;

import com.pwpew.entity.TMessage;
import com.pwpew.entity.TNotice;
import com.pwpew.modeldriven.MessageMd;
import com.pwpew.modeldriven.NoticeMd;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/16 - 13:23
 * @commet:
 */
public interface MessageDao {
    //增删改查
    //查询所有留言
    public List<TMessage> findMessage();
    public TMessage getMessageById(int id);
    public void updateMessage(TMessage message);
    public void deleteMessage(TMessage message);
    public void insertMessage(TMessage message);

    //分页查询留言
    public List<TMessage> findMessageByPage(MessageMd messageMd, int firstResult, int maxResults);
    //查询用户留言，用于分页
    public Long findMessageCount(MessageMd messageMd);
    // 查询特定用户的留言
    public List<TMessage> findMessageByUser(int userId, int firstResult, int maxResults);
}
