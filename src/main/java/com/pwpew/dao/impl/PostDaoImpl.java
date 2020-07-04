package com.pwpew.dao.impl;

import com.pwpew.dao.PostDao;
import com.pwpew.entity.TPost;
import com.pwpew.modeldriven.PostMd;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("postDao")
public class PostDaoImpl extends HibernateDaoSupport implements PostDao {

    private Session session = null;
    //注入父类的模板对象
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将noticeDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    // 获取帖子列表
    public List<TPost> findPostList() {
        List<TPost> list = new ArrayList<TPost>();
        session = this.getSessionFactory().getCurrentSession();
//        list = session.createQuery("from TPost p, TUser u where p.userId = u.userId").list();
        list = session.createQuery("from Tpost p inner join fetch TUser u where p.statue=1").list();
        return list;
    }

    // 获取帖子
    public TPost getPostById(int postId) {
        this.getHibernateTemplate().clear();
        session = this.getSessionFactory().getCurrentSession();
        TPost post =  session.get(TPost.class, postId);
        return post;
    }

    //获取特定类型的帖子
    public List<TPost> getPostByPostType(String postType, int maxResults, int firstResult) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from TPost where postType = ? and effectiveness = '1' and statue = '1' order by postId desc");
        query.setParameter(0, postType);
        query.setMaxResults(maxResults);
        query.setFirstResult(firstResult);
        List<TPost> list = query.list();
        return list;
    }

    // 发帖
    public void insertPost(TPost post) {
        session = this.getSessionFactory().getCurrentSession();
        session.save(post);
    }

    @Override
    public void deletePost(TPost post) {

        //先查询再删除
        post = this.getHibernateTemplate().get(TPost.class, post.getPostId());

        //查询不为空就删除
        if (post != null) {
            this.getHibernateTemplate().delete(post);
        }

    }

    @Override
    public void updatePost(TPost post) {

        //更新的时候要注意数据丢失的情况
        this.getHibernateTemplate().clear();
        this.getHibernateTemplate().update(post);
    }

    @Override
    public List<TPost> findPostByPage(PostMd postMd, int firstResult, int maxResults) {

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        Session session = this.getSessionFactory().getCurrentSession();


        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("from TPost t where 1=1 ");

        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findPostCondition(postMd, queryString, params);

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

    }

    @Override
    public Long findPostCount(PostMd postMd) {

        //使用hql查询
        StringBuffer queryString = new StringBuffer();
        queryString.append("select count(*) from TPost t where 1=1");

        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findPostCondition(postMd, queryString, params);

        List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
        Long total = (Long) list.get(0);
        return total;
    }

    @Override
    public void updateStatueAndEff(PostMd postMd) {

        TPost post = this.getHibernateTemplate().get(TPost.class, postMd.getPostId());
        if (post.getEffectiveness() != null) {
            post.setStatue(postMd.getStatue());
        }

        if (post.getStatue() != null) {
            post.setEffectiveness(postMd.getEffectiveness());
        }

        this.getHibernateTemplate().update(post);
    }

    private void findPostCondition(PostMd postMd, StringBuffer queryString, List<Object> params) {

        //拼装 查询条件，拼装 查询条件同时拼装 参数
        if (postMd != null) {

            //帖子类型
            if (StringUtils.isNotEmpty(postMd.getPostType())) {
                queryString.append(" and  t.postType =  ? ");
                params.add(postMd.getPostType());
            }

            //姓名
            if (StringUtils.isNotEmpty(postMd.getPostName())) {
                queryString.append(" and  t.postName like  ? ");
                params.add("%" + postMd.getPostName() + "%");
            }

            //性别
            if (StringUtils.isNotEmpty(postMd.getPostGender())) {
                queryString.append(" and  t.postGender = ? ");
                params.add(postMd.getPostGender());
            }


            //起始时间
            if (postMd.getPostDate() != null) {

                queryString.append(" and t.missingtime >= ? ");
                params.add(postMd.getPostDate());
            }

            //结束时间
            if (postMd.getPostDate_end() != null) {

                queryString.append(" and t.missingtime <= ? ");
                params.add(postMd.getPostDate_end());
            }

            //举报状态
            if (StringUtils.isNotEmpty(postMd.getEffectiveness())) {

                queryString.append(" and t.effectiveness = ?");
                params.add(postMd.getEffectiveness());
            }

            //封禁状态
            if (StringUtils.isNotEmpty(postMd.getStatue())) {

                queryString.append(" and t.statue = ?");
                params.add(postMd.getStatue());
            }

            if (StringUtils.isEmpty(postMd.getStatue())) {
                queryString.append(" and t.statue = 1");
            }

        }
    }

    // 回帖
    public void replyPost(int postId) {
        session = this.getSessionFactory().getCurrentSession();
        // todo
    }

    public List<TPost> findPostByNameOrDescribe(String nameOrDescribe, int firestResult, int maxResult) {
        nameOrDescribe = nameOrDescribe.replace(" ", "");
        Session session = this.getSessionFactory().getCurrentSession();
        String hql="from TPost where postName like '%" + nameOrDescribe + "%' or postDescribe like '%" +nameOrDescribe +"%' or postGender = '" + nameOrDescribe+"' or postProvince like '%" +nameOrDescribe+"%' or postCity like '%"+nameOrDescribe+"%'";
        Query query = session.createQuery(hql);
        query.setFirstResult(firestResult);
        query.setMaxResults(maxResult);
        List<TPost> list = query.list();
        return list;
    }

    @Override
    public List<TPost> findPostListByUserId(int userId, int firestResult, int maxResult) {

        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from TPost t where t.user.userId = ?");

        query.setParameter(0,userId);
        query.setFirstResult(firestResult);
        query.setMaxResults(maxResult);
        List<TPost> list = query.list();
        return list;
    }

    @Override
    public Long findPostCountByUserId(int userId) {

        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("select count(*) from TPost t where t.user.userId=? ");
        query.setParameter(0, userId);
        return (Long) query.list().get(0);
    }


}
