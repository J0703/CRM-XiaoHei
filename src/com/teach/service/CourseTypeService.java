package com.teach.service;

import com.lanou.service.BaseService;
import com.lanou.util.PageBean;
import com.teach.domain.CourseType;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
public interface CourseTypeService extends BaseService<CourseType> {

    PageBean<CourseType> findAllCourseType(CourseType courseType, int pageNum, int pageSize);

    PageBean<CourseType> findQuery(CourseType courseType, int pageNum, int pageSize, Map<String,Object> params);

    CourseType findCourseTypeById(Serializable id);

    void saveCourseType(CourseType courseType);
}
