package com.pwpew.dao.impl;

import com.pwpew.dao.CommentDao;
import com.pwpew.entity.TComment;
import com.pwpew.modeldriven.CommentMd;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/21 - 14:03
 * @commet:
 */
@Repository("commentDao")
public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

    private Session session = null;

    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将commentDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    public void insertComment(TComment comment){
        session = this.getSessionFactory().getCurrentSession();
        session.save(comment);
    }


    public Long findCommentCount(CommentMd commentMd, int postId){
        session = this.getSessionFactory().getCurrentSession();

        //使用hql查询

        Query query = session.createQuery("select count(*) from TComment t where t.postId=?");
        query.setParameter(0, postId);

        return (Long)query.list().get(0);


    }

    public List<TComment> findCommentByPage(int postId , int firstResult, int maxResults) {

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        session = this.getSessionFactory().getCurrentSession();

        //使用hql查询

        Query query = session.createQuery("from TComment t where t.postId=?");
        query.setParameter(0, postId);

        //设置分页参数
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        //直接使用原始的query对象查询

        return query.list();

    }


}
