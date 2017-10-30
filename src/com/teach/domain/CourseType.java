package com.teach.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dllo on 17/10/28.
 */
public class CourseType {

    private String courseTypeID;

    private double courseCost;

    private int total;

    private String courseName;

    private String remark;

    private Set<Research> researches = new HashSet<>();

    public Set<Research> getResearches() {
        return researches;
    }

    public void setResearches(Set<Research> researches) {
        this.researches = researches;
    }

    public String getCourseTypeID() {
        return courseTypeID;
    }

    public void setCourseTypeID(String courseTypeID) {
        this.courseTypeID = courseTypeID;
    }

    public double getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(double courseCost) {
        this.courseCost = courseCost;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeID='" + courseTypeID + '\'' +
                ", courseCost=" + courseCost +
                ", total=" + total +
                ", courseName='" + courseName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
