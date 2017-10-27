package com.lanou.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dllo on 17/10/24.
 */
public class Post {

    private String postId;

    private String postName;

    private Department department;

    private Set<Staff> staffs = new HashSet<>();

    public Post() {
    }

    public Post(String postName, Department department) {
        this.postName = postName;
        this.department = department;
    }

    public Post(String postId, String postName, Department department) {
        this.postId = postId;
        this.postName = postName;
        this.department = department;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", department=" + department +
                '}';
    }
}
