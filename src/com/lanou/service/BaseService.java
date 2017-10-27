package com.lanou.service;

import com.lanou.util.PageBean;

/**
 * Created by dllo on 17/10/26.
 */
public interface BaseService<T> {

    /**
     * 查询所有 -- 分页查询
     * @param pageNum 当前的页
     * @param pageSize 每页显示的条目数 * @return
     * @param hql 查询条数的 hql 语句
     */
    PageBean<T> findAll(T t, int pageNum, int pageSize, String hql, String condition);

}
