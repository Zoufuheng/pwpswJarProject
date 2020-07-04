package com.pwpew.dao.impl;

import com.pwpew.dao.NoticeDao;
import com.pwpew.entity.TNotice;
import com.pwpew.modeldriven.NoticeMd;
import com.pwpew.modeldriven.UserMd;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 9:57
 * @commet:
 */
@Repository("noticeDao")
public class NoticeDaoImpl extends HibernateDaoSupport implements NoticeDao {
    //注入父类的模板对象
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将noticeDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    //QBC查询初始化
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(TNotice.class);
    }


    @Override
    public List<TNotice> findNoticeByPage(NoticeMd noticeMd, int firstResult, int maxResults) {

        //如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
        Session session = this.getSessionFactory().getCurrentSession();
        //使用hql查询
        StringBuffer queryString = new StringBuffer();

        queryString.append("from TNotice t  where 1=1 ");
        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findNoticeCondition(noticeMd, queryString, params);

        //进行排序拼接
        queryString.append("order by ntsDate desc");

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
    public Long findNoticeCount(NoticeMd noticeMd) {

        //使用hql查询
        StringBuffer queryString = new StringBuffer();
        queryString.append("select count(*) from TNotice t  where 1=1 ");
        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findNoticeCondition(noticeMd, queryString, params);
        List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
        Long total = (Long) list.get(0);
        return total;
    }

    private void findNoticeCondition(NoticeMd noticeMd, StringBuffer queryString, List<Object> params) {
        //拼装 查询条件，拼装 查询条件同时拼装 参数
        if (noticeMd != null) {

            //标题
            if (StringUtils.isNotEmpty(noticeMd.getNtsHeadLine())) {
                queryString.append(" and  t.ntsHeadLine like  ? ");
                params.add("%" + noticeMd.getNtsHeadLine() + "%");
            }
            //起始时间
            if (noticeMd.getNtsDate() != null) {

                queryString.append(" and t.ntsDate >= ? ");
                params.add(noticeMd.getNtsDate());
            }

            //结束时间
            if (noticeMd.getNtsDate_end() != null) {

                queryString.append(" and t.ntsDate <= ? ");
                params.add(noticeMd.getNtsDate_end());
            }

            if (noticeMd.getAdministrator() != null) {
                if (noticeMd.getAdministrator().getAdminId() != 0) {
                    queryString.append(" and t.administrator.adminId = ? ");
                    params.add(noticeMd.getAdministrator().getAdminId());
                }
            }
        }

    }

    @Override
    public List<TNotice> findNotice() {
        DetachedCriteria detachedCriteria = this.createDetachedCriteria();
        return (List<TNotice>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public TNotice getNotice(int id) {
        return this.getHibernateTemplate().get(TNotice.class, id);
    }

    @Override
    public void updateNotice(TNotice notice) {

        this.getHibernateTemplate().update(notice);
    }

    @Override
    public void deleteNotice(TNotice notice) {

        //先根据主键查询，再进行删除
        //TNotice tNotice = this.getHibernateTemplate().get(TNotice.class,notice.getNtsId());
        notice = this.getHibernateTemplate().get(TNotice.class, notice.getNtsId());
        if (notice != null) {
            this.getHibernateTemplate().delete(notice);
        }

    }

    @Override
    public void insertNotice(TNotice notice) {

        this.getHibernateTemplate().save(notice);
    }

    //查询最新发布的通告
    @Override
    public TNotice findLatestNotice(NoticeMd noticeMd) {
        //使用hql查询
        StringBuffer queryString = new StringBuffer();
        queryString.append("from TNotice t  order by ntsDate desc");
        //定义List存放参数
        List<Object> params = new ArrayList<Object>();

        //拼装 查询条件
        findNoticeCondition(noticeMd, queryString, params);
        List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
        TNotice tNotice = (TNotice) list.get(0);
        return tNotice;
    }

    //查询上一条通告
    @Override
    public TNotice findLastNotice(NoticeMd noticeMd,Timestamp ntsDate) {
        //使用hql查询
        StringBuffer queryString = new StringBuffer();
        queryString.append("from TNotice t where t.ntsDate > ?  order by ntsDate");
        List list = this.getHibernateTemplate().find(queryString.toString(), ntsDate);
        if(list.size()==0){
            return null;
        }
        TNotice tNotice = (TNotice) list.get(0);
        return tNotice;
    }

    //查询下一条通告
    @Override
    public TNotice findNextNotice(NoticeMd noticeMd,Timestamp ntsDate) {
        //使用hql查询
        StringBuffer queryString = new StringBuffer();
        queryString.append("from TNotice t where t.ntsDate < ?  order by ntsDate desc");
        List list = this.getHibernateTemplate().find(queryString.toString(), ntsDate);
        if(list.size()==0){
            return null;
        }
        TNotice tNotice = (TNotice) list.get(0);
        return tNotice;
    }

//QBC查询,日期高级查询有问题。
    /*   private void findNoticeCondition(DetachedCriteria detachedCriteria,NoticeMd noticeMd){

           if(noticeMd!=null){
               if (StringUtils.isNotEmpty(noticeMd.getNtsHeadLine())){
                   detachedCriteria.add(Restrictions.like("ntsHeadLine",noticeMd.getNtsHeadLine(), MatchMode.ANYWHERE));
               }

               if(noticeMd.getNtsDate()!=null){

                   String date = String.valueOf(noticeMd.getNtsDate());
                   String [] split = date.split(" ");
                   date = split[0];
                   System.out.println(date);

                   //Timestamp time = Timestamp.valueOf(date);
                   //detachedCriteria.add(Restrictions.between("ntsDate", date, "2020-05-14"));
                   detachedCriteria.add(Restrictions.gt("ntsDate", date));
                   //detachedCriteria.add(Restrictions.like("ntsDate",date,MatchMode.ANYWHERE));
               }

               if(noticeMd.getAdministrator()!=null){
                   if(noticeMd.getAdministrator().getAdminId()!=0){
                       detachedCriteria.add(Restrictions.eq("administrator.adminId",noticeMd.getAdministrator().getAdminId()));
                   }

               }
           }

       }*/
  /*  @Override
    public List<TNotice> findNoticeByPage(NoticeMd noticeMd, int firstResult, int maxResults) {
        DetachedCriteria detachedCriteria = this.createDetachedCriteria();
        // 根据NoticeMd中数据拼接查询条件，使用detachedCriteria
        // 创建detachedCriteria
        DetachedCriteria detachedCriteria1 = this.createDetachedCriteria();
        // 拼装 查询条件
        this.findNoticeCondition(detachedCriteria, noticeMd);

        return (List<TNotice>) this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult,
                maxResults);
    }

    @Override
    public Long findNoticeCount(NoticeMd noticeMd) {
        // 根据NoticeMd中数据拼接查询条件，使用detachedCriteria
        // 创建detachedCriteria
        DetachedCriteria detachedCriteria = this.createDetachedCriteria();
        //拼装条件
        this.findNoticeCondition(detachedCriteria, noticeMd);

        // 设置投影列
        detachedCriteria.setProjection(Projections.rowCount());
        // 查询
        List list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long total = (Long) list.get(0);
        return total;
    }*/
}
