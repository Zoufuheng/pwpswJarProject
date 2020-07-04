package com.pwpew.dao.impl;

import com.pwpew.dao.DatadictDao;
import com.pwpew.entity.TDatadict;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author miaoyin
 * @date 2020/5/21 - 17:30
 * @commet:
 */
@Repository("datadict")
public class DatadictDaoImpl extends HibernateDaoSupport implements DatadictDao {
    @Autowired
    public void setHT(HibernateTemplate hibernateTemplate) {
        // 将adminDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
        this.setHibernateTemplate(hibernateTemplate);
    }

    @Override
    public List<TDatadict> findByDictTypeCode(String dictTypeCode) {

        Session session = this.getSessionFactory().getCurrentSession();

        String hql = "from TDatadict t where t.dictTypeCode = ?";


        Query query = session.createQuery(hql);

        query.setParameter(0, dictTypeCode);

        return query.list();
    }
}
