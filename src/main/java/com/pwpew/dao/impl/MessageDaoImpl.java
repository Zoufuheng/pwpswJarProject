package com.pwpew.dao.impl;

import com.pwpew.dao.MessageDao;
import com.pwpew.entity.TAdministrator;
import com.pwpew.entity.TMessage;
import com.pwpew.entity.TNotice;
import com.pwpew.entity.TUser;
import com.pwpew.modeldriven.MessageMd;
import com.pwpew.modeldriven.NoticeMd;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/16 - 13:28
 * @commet:
 */
@Repository("messageDao")
public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao {
    private Session session = null;
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将adminDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    //QBC查询初始化
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(TMessage.class);
    }

    @Override
    public List<TMessage> findMessage() {
        return null;
    }

    @Override
    public TMessage getMessageById(int id) {

        return this.getHibernateTemplate().get(TMessage.class, id);
    }

    @Override
    public void updateMessage(TMessage message) {

        this.getHibernateTemplate().update(message);
    }

    @Override
    public void deleteMessage(TMessage message) {

        //先进行查询再删除
        message = this.getHibernateTemplate().get(TMessage.class, message.getMsgId());

        if(message != null){
            this.getHibernateTemplate().delete(message);
        }

    }

    @Override
    public void insertMessage(TMessage message) {
        session = this.getSessionFactory().getCurrentSession();
        session.save(message);
    }

    private void findNoticeCondition(MessageMd messageMd, StringBuffer queryString, List<Object> params) {
        //拼装 查询条件，拼装 查询条件同时拼装 参数
        if (messageMd != null) {

            //留言是否回复
            if (messageMd.getMsgIsRes() == 1) {
                queryString.append(" and  t.msgResMain is not null  ");
            }

            //留言是否回复d
            if (messageMd.getMsgIsRes() == 2) {
                queryString.append(" and t.msgResMain is null");
            }

            //起始时间
            if (messageMd.getMsgDate() != null) {

                queryString.append(" and t.msgDate >= ? ");
                params.add(messageMd.getMsgDate());
            }

            //结束时间
            if (messageMd.getMsgDate_end() != null) {

                queryString.append(" and t.msgDate <= ? ");
                params.add(messageMd.getMsgDate_end());
            }

        }

    }

    @Override
    public List<TMessage> findMessageByPage(MessageMd messageMd, int firstResult, int maxResults) {

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        Session session = this.getSessionFactory().getCurrentSession();
        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("from TMessage t  where 1=1 ");
        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findNoticeCondition(messageMd, queryString, params);

        Query query = session.createQuery(queryString.toString());

        //参数绑定
        //遍历params，进行每个参数绑定
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
        //设置分页参数
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        //直接使用原始的query对象查询

        return query.list();
        //参数：1：hql语句，2：传入的参数，数组类型
        //return (List<Object>) this.getHibernateTemplate().find(queryString.toString(), params.toArray());
    }

    @Override
    public Long findMessageCount(MessageMd messageMd) {

        //使用hql查询
        StringBuffer queryString = new StringBuffer();
        queryString.append("select count(*) from TMessage t  where 1=1 ");
        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findNoticeCondition(messageMd, queryString, params);
        List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
        Long total = (Long) list.get(0);
        return total;
    }

    @Override
    public List<TMessage> findMessageByUser(int userId, int firstResult, int maxResults){

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        session = this.getSessionFactory().getCurrentSession();

        //使用hql查询
        Query query = session.createQuery("from TMessage t where t.user.userId=?");
        query.setParameter(0, userId);

        //设置分页参数
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        //直接使用原始的query对象查询
        List<TMessage> list = query.list();

        return list;

    }

}
