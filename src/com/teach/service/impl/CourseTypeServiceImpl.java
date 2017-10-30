package com.teach.service.impl;

import com.lanou.service.impl.BaseServiceImpl;
import com.lanou.util.PageBean;
import com.teach.dao.CourseTypeDao;
import com.teach.domain.CourseType;
import com.teach.service.CourseTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
public class CourseTypeServiceImpl extends BaseServiceImpl<CourseType> implements CourseTypeService {

    private CourseTypeDao courseTypeDao;

    private List<Object> param;

    public CourseTypeDao getCourseTypeDao() {
        return courseTypeDao;
    }

    public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
        this.courseTypeDao = courseTypeDao;
        super.setBaseDao(courseTypeDao);
    }

    @Override
    public PageBean<CourseType> findAllCourseType(CourseType courseType, int pageNum, int pageSize) {

        if (pageNum == 0) pageNum++;

        String hql = "select count(c) from CourseType c";

        String condition = "from CourseType";

        return super.findAll(courseType, pageNum, pageSize, hql, condition);

    }

    @Override
    public PageBean<CourseType> findQuery(CourseType courseType, int pageNum, int pageSize, Map<String, Object> params) {

        if (pageNum == 0) pageNum++;

        String condition = "from CourseType c where 1=1";

        condition = advancedQuery(params,condition);

        String hql = "select count(c) " + condition;

        int totalRecord = courseTypeDao.getTotalRecord(hql, param.toArray());

        PageBean<CourseType> pageBean = new PageBean<>(pageNum,pageSize,totalRecord);

        List<CourseType> data = courseTypeDao.findAll(condition, param.toArray(), pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(data);

        return pageBean;
    }

    /* 拼接字符串,并封装参数的方法 */
    private String advancedQuery(Map<String, Object> params, String str) {

        param = new ArrayList<>();

        StringBuffer sb = new StringBuffer(str);

        String courseName = (String) params.get("courseName");

        if (!courseName.equals("")) {

            sb.append(" and courseName like ?");

            param.add("%" + courseName + "%");

        }

        String remark = (String) params.get("remark");

        if (!remark.equals("remark")) {


            sb.append(" and remark like ?");

            param.add("%" + remark + "%");

        }

        int totalStart = (int) params.get("totalStart");

        if (totalStart != 0) {

            sb.append(" and total>=?");

            param.add(totalStart);

        }

        int totalEnd = (int) params.get("totalEnd");

        if (totalEnd != 0) {

            sb.append(" and total<=?");

            param.add(totalEnd);

        }

        double lessonCostStart = (double) params.get("lessonCostStart");

        if (lessonCostStart != 0.0) {

            sb.append(" and courseCost>=?");

            param.add(lessonCostStart);

        }

        double lessonCostEnd = (double) params.get("lessonCostEnd");

        if(lessonCostEnd != 0.0){

            sb.append(" and courseCost<=?");

            param.add(lessonCostEnd);

        }

        return sb.toString();

    }
}
