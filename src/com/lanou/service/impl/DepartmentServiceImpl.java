package com.lanou.service.impl;

import com.lanou.dao.DepartmentDao;
import com.lanou.domain.Department;
import com.lanou.service.DepartmentService;
import com.lanou.util.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    private DepartmentDao departmentDao;


    /* 覆写父类findAll方法 */
    @Override
    public PageBean<Department> findAll(Department department, int pageNum, int pageSize) {

        if(pageNum == 0) pageNum ++;

        String hql = "select count(d) from Department d";

        String condition = "from Department";

        return super.findAll(department, pageNum, pageSize, hql, condition);
    }

    @Override
    public List<Department> findAllDepart() {

        String hql = "from Department";

        return departmentDao.findAll(hql);
    }

    @Override
    public void addOrEditDepart(Department department) {

        if (department.getDepID().equals("")) {

            departmentDao.add(department);

        } else {

            departmentDao.update(department);

        }
    }

    @Override
    public Department findDepartById(Serializable id) {

        return departmentDao.findById(id,Department.class);

    }


    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {

        this.departmentDao = departmentDao;

        super.setBaseDao(departmentDao);

    }
}
