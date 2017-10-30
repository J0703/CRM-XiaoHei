package com.teach.action;

import com.lanou.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teach.domain.CourseType;
import com.teach.domain.Research;
import com.teach.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
@Controller("courseTypeAction")
@Scope("prototype")
public class CourseTypeAction extends ActionSupport implements ModelDriven<CourseType> {

    @Autowired
    @Qualifier("courseTypeService")
    private CourseTypeService courseTypeService;

    private CourseType courseType;

    private int pageNum;

    private int pageSize = 3;

    private int totalStart;

    private int totalEnd;

    private double lessonCostStart;

    private double lessonCostEnd;

    private Map<String,Object> params;


    /* 遍历所有课程 */
    public String findAllCourseType(){

        PageBean<CourseType> pageBean = courseTypeService.findAllCourseType(courseType, pageNum, pageSize);

        System.out.println(pageBean.getData().toString());

        ActionContext.getContext().put("pageBean", pageBean);

        return SUCCESS;

    }



    /* 高级查询-指定课程 */
    public String advancedQueryCourseType(){

        packageParams();

        PageBean<CourseType> pageBean = courseTypeService.findQuery(courseType, pageNum, pageSize, params);

        ActionContext.getContext().put("pageBean", pageBean);

        return SUCCESS;

    }

    /* 封装Map传递参数方法 */
    private void packageParams(){

        params = new HashMap<>();

        params.put("courseName",courseType.getCourseName());

        params.put("remark",courseType.getRemark());

        params.put("totalStart",totalStart);

        params.put("totalEnd",totalEnd);

        params.put("lessonCostStart",lessonCostStart);

        params.put("lessonCostEnd",lessonCostEnd);

    }

    @Override
    public CourseType getModel() {

        courseType = new CourseType();

        return courseType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalStart() {
        return totalStart;
    }

    public void setTotalStart(int totalStart) {
        this.totalStart = totalStart;
    }

    public int getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(int totalEnd) {
        this.totalEnd = totalEnd;
    }

    public double getLessonCostStart() {
        return lessonCostStart;
    }

    public void setLessonCostStart(double lessonCostStart) {
        this.lessonCostStart = lessonCostStart;
    }

    public double getLessonCostEnd() {
        return lessonCostEnd;
    }

    public void setLessonCostEnd(double lessonCostEnd) {
        this.lessonCostEnd = lessonCostEnd;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
