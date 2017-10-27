package com.lanou.service;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService {

    Staff login(String loginName, String loginPwd);

    List<Staff> findAllStaff();

    Staff findStaffById(Serializable id);

    void addStaff(Post post, Staff staff);

    List<Staff> advancedQuery(String depID, String postId, String staffName);
}
