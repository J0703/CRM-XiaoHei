package com.lanou.dao.impl;

import com.lanou.dao.BaseDao;
import com.lanou.util.PageHibernateCallback;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    @Override
    public List<T> findAll(String hql) {

        List<T> tList = (List<T>) getHibernateTemplate().find(hql);

        return tList;
    }

    @Override
    public List<T> find(String hql, Object[] params) {

        List<T> tList = (List<T>) getHibernateTemplate().find(hql, params);

        return tList;
    }

    @Override
    public T findSingle(String hql, Object[] params) {

        List<T> tList = (List<T>) getHibernateTemplate().find(hql, params);

        if (tList.size() > 0) return tList.get(0);

        return null;
    }

    @Override
    public T findById(Serializable id, Class<T> tClass) {

        Session session = currentSession();

        T t = (T) session.get(tClass, id);

        return t;
    }

    @Override
    public void add(T t) {

        getHibernateTemplate().save(t);

    }

    @Override
    public void update(T t) {

        Session session = currentSession();

        session.merge(t);

    }

    @Override
    public int getTotalRecord(String hql, Object[] params) {

        List<Long> find;

        if (params == null) {

            find = (List<Long>) this.getHibernateTemplate().find(hql);

        } else {

            find = (List<Long>) this.getHibernateTemplate().find(hql, params);
        }

        if (find != null) {

            return find.get(0).intValue();

        }

        return 0;

    }

    @Override
    public List<T> findAll(String hql, Object[] params, int startIndex, int pageSize) {

        //hql是没有分页函数的,我们可以使用execute来实现
        return this.getHibernateTemplate().execute(

                new PageHibernateCallback<T>(hql, params, startIndex, pageSize));
    }
}
