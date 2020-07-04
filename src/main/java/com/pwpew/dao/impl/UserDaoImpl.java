package com.pwpew.dao.impl;

import com.pwpew.dao.UserDao;
import com.pwpew.entity.TPost;
import com.pwpew.entity.TUser;
import com.pwpew.modeldriven.UserMd;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/8 - 9:46
 * @commet:
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {


    //注入父类的模板对象
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将userDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    //QBC查询初始化
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(TUser.class);
    }

    @Override
    public List<TUser> findUser() {

        DetachedCriteria detachedCriteria = this.createDetachedCriteria();
        return (List<TUser>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public TUser getUser(TUser user) {
        return this.getHibernateTemplate().get(TUser.class, user.getUserId());
    }

    @Override
    public void updateUser(TUser user) {

        this.getHibernateTemplate().update(user);
    }

    @Override
    public void deleteUser(TUser user) {

        this.getHibernateTemplate().delete(user);
    }

    @Override
    public void insertUser(TUser user) {

        this.getHibernateTemplate().save(user);
    }

    private void findPostCondition(UserMd userMd, StringBuffer queryString, List<Object> params) {

        //拼装 查询条件，拼装 查询条件同时拼装 参数
        if (userMd != null) {

            if (StringUtils.isNotEmpty(userMd.getUsername())) {
                queryString.append(" and t.username like ?");
                params.add("%" + userMd.getUsername() + "%");
            }

            if (StringUtils.isNotEmpty(userMd.getUserGender())) {
                queryString.append(" and t.userGender = ?");
                params.add(userMd.getUserGender());
            }

            if (StringUtils.isNotEmpty(userMd.getUserProvince())) {
                queryString.append(" and t.userProvince like ?");
                params.add("%" + userMd.getUserProvince() + "%");
            }

            if (StringUtils.isNotEmpty(userMd.getUserCity())) {
                queryString.append(" and t.userCity like ?");
                params.add("%" + userMd.getUserCity() + "%");
            }

        }


    }

    //分页查询
    @Override
    public List<TUser> findUserByPage(UserMd user, int firstResult, int maxResults) {

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        Session session = this.getSessionFactory().getCurrentSession();


        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("from TUser t where 1=1 ");

        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findPostCondition(user, queryString, params);

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

    //查询用户总数，可用于分页
    @Override
    public Long findUserCount(UserMd user) {
        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("select count(*) from TUser t where 1=1");

        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findPostCondition(user, queryString, params);

        List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
        Long total = (Long) list.get(0);
        return total;
    }

    public TUser findUserByPostId(int postId) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from TPost where postId = ?").setParameter(0, postId);
        List<TPost> list = query.list();
        TPost post = list.get(0);
        TUser user = post.getUser();
        return user;
    }

    //在账号管理处进行个人信息更新
    public int updateUserOfAccount(TUser user) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("update TUser set username = ?,userNickname = ?,userGender = ?,userAge = ?,userPhone = ?,userProvince = ?,userCity = ?,userPicture = ?,email = ? where userId =" + user.getUserId());
        query.setParameter(0, user.getUsername());
        query.setParameter(1, user.getUserNickname());
        query.setParameter(2, user.getUserGender());
        query.setParameter(3, user.getUserAge());
        query.setParameter(4, user.getUserPhone());
        query.setParameter(5, user.getUserProvince());
        query.setParameter(6, user.getUserCity());
        query.setParameter(7, user.getUserPicture());
        query.setParameter(8, user.getEmail());
        int i = query.executeUpdate();
        return i;
    }

    @Override
    public TUser getUserByEmail(String email) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from TUser t where t.email=? ");
        query.setParameter(0, email);

        return (TUser) query.list().get(0);
    }

    @Override
    public TUser getUserById(int userId) {

        return this.getHibernateTemplate().get(TUser.class, userId);
    }

}
