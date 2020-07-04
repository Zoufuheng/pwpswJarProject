package com.pwpew.service;

import com.pwpew.entity.TMessage;
import com.pwpew.modeldriven.MessageMd;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/16 - 13:35
 * @commet:
 */
public interface MessageService {

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
    public List<TMessage> findMessageByUser(int userId, int firstResult, int maxResults);
}
