package com.pwpew.dao.impl;

import com.pwpew.dao.AdminDao;
import com.pwpew.entity.TAdministrator;
import com.pwpew.entity.TNotice;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/13 - 12:53
 * @commet:
 */
@Repository("adminDao")
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将adminDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    //QBC查询初始化
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(TAdministrator.class);
    }

    @Override
    public List<TAdministrator> findAdmin() {
        DetachedCriteria detachedCriteria = this.createDetachedCriteria();

        return (List<TAdministrator>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public TAdministrator getAdminById(int id) {
        return this.getHibernateTemplate().get(TAdministrator.class,id);
    }

    @Override
    public void updateAdmin(TAdministrator admin) {

        this.getHibernateTemplate().update(admin);
    }
}
