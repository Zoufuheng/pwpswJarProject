package com.pwpew.dao.impl;


import com.pwpew.dao.VolunteerDao;
import com.pwpew.entity.TUser;
import com.pwpew.entity.TVolunteer;
import com.pwpew.modeldriven.PostMd;
import com.pwpew.modeldriven.VolunteerMd;
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
 * @author Yani
 * @date 2020/5/24 - 9:46
 * @commet:
 */
@Repository("volunteerDao")
public class VolunteerDaoImpl extends HibernateDaoSupport implements VolunteerDao {

    //注入父类的模板对象
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将userDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    //QBC查询初始化
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(TVolunteer.class);
    }

    @Override
    public List<TVolunteer> findVolunteer() {
        DetachedCriteria detachedCriteria = this.createDetachedCriteria();
        return (List<TVolunteer>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public TVolunteer getVolunteer(int volId) {
        return this.getHibernateTemplate().get(TVolunteer.class, volId);
    }

    @Override
    public void updateVolunteer(TVolunteer volunteer) {
        this.getHibernateTemplate().update(volunteer);
    }

    @Override
    public void deleteVolunteer(TVolunteer volunteer) {
        this.getHibernateTemplate().delete(volunteer);
    }

    @Override
    public void insertVolunteer(TVolunteer volunteer) {
        this.getHibernateTemplate().save(volunteer);
    }

    private void findPostCondition(VolunteerMd volMd, StringBuffer queryString, List<Object> params) {

        //拼装 查询条件，拼装 查询条件同时拼装 参数
        if (volMd != null) {

            if (StringUtils.isNotEmpty(volMd.getVolname())) {
                queryString.append(" and t.volname like ?");
                params.add("%" + volMd.getVolname() + "%");
            }

            if (StringUtils.isNotEmpty(volMd.getVolSex())) {
                queryString.append(" and t.volSex = ?");
                params.add(volMd.getVolSex());
            }

            if (StringUtils.isNotEmpty(volMd.getVolProvince())) {
                queryString.append(" and t.volProvince like ?");
                params.add("%" + volMd.getVolProvince() + "%");
            }

            if (StringUtils.isNotEmpty(volMd.getVolCity())) {
                queryString.append(" and t.volCity like ?");
                params.add("%" + volMd.getVolCity() + "%");
            }

        }


    }

    @Override
    public List<TVolunteer> findUserByPage(VolunteerMd volMd, int firstResult, int maxResults) {

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        Session session = this.getSessionFactory().getCurrentSession();


        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("from TVolunteer t where 1=1 ");

        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findPostCondition(volMd, queryString, params);

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
    public Long findUserCount(VolunteerMd volMd) {
        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("select count(*) from TVolunteer t where 1=1");

        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findPostCondition(volMd, queryString, params);

        List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
        Long total = (Long) list.get(0);
        return total;
    }

}
