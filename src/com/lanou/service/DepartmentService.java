package com.lanou.service;

import com.lanou.domain.Department;
import com.lanou.util.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface DepartmentService extends BaseService<Department>{

    List<Department> findAllDepart();

    void addOrEditDepart(Department department);

    Department findDepartById(Serializable id);

    PageBean<Department> findAll(Department department, int pageNum, int pageSize);

}
