package com.pwpew.service.impl;

import com.pwpew.dao.MessageDao;
import com.pwpew.entity.TMessage;
import com.pwpew.modeldriven.MessageMd;
import com.pwpew.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/16 - 13:37
 * @commet:
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<TMessage> findMessage() {
        return null;
    }

    @Override
    public TMessage getMessageById(int id) {

        TMessage message = messageDao.getMessageById(id);
        if(message.getUser().getUserPassword()!=null){
            message.getUser().setUserPassword(null);
        }
        return message;
    }

    @Override
    public void updateMessage(TMessage message) {

        if (message == null) {
            throw new RuntimeException("传入的留言参数不合法");
        }
        if (StringUtils.isEmpty(message.getMsgResMain()) || message.getMsgResTime() == null) {
            throw new RuntimeException("传入的留言参数不合法");
        }

        messageDao.updateMessage(message);
    }

    @Override
    public void deleteMessage(TMessage message) {

        messageDao.deleteMessage(message);
    }

    @Override
    public void insertMessage(TMessage message) {
        messageDao.insertMessage(message);
    }

    @Override
    public List<TMessage> findMessageByPage(MessageMd messageMd, int firstResult, int maxResults) {
        List<TMessage> list = messageDao.findMessageByPage(messageMd, firstResult, maxResults);

        //去掉查询里面用户的密码
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser().getUserPassword() != null) {
                list.get(i).getUser().setUserPassword(null);
            }
        }
        return list;
    }

    @Override
    public Long findMessageCount(MessageMd messageMd) {
        return messageDao.findMessageCount(messageMd);
    }

    @Override
    public List<TMessage> findMessageByUser(int userId, int firstResult, int maxResults){
        List<TMessage> list = messageDao.findMessageByUser(userId, firstResult, maxResults);
        return list;
    }
}
