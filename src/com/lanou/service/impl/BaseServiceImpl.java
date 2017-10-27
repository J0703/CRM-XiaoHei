package com.lanou.service.impl;

import com.lanou.dao.BaseDao;
import com.lanou.service.BaseService;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/10/26.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    @Override
    public PageBean<T> findAll(T t, int pageNum, int pageSize, String hql, String condition) {

        int totalRecord = baseDao.getTotalRecord(hql,null);

        PageBean<T> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);

        List<T> data = baseDao.findAll(condition,null,pageBean.getStartIndex(),pageBean.getPageSize());

        pageBean.setData(data);

        return pageBean;
    }

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }
}
