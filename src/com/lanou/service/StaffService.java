package com.lanou.service;


import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.util.PageBean;

import java.io.Serializable;
import java.util.Map;


/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService extends BaseService<Staff> {

    Staff login(String loginName, String loginPwd);

    PageBean<Staff> findAllStaff(Staff staff, int pageNum, int pageSize);

    Staff findStaffById(Serializable id);

    void addStaff(Post post, Staff staff);

    PageBean<Staff> findQuery(Staff staff, int pageNum, int pageSize, Map<String,String> param);

    String advancedQuery(String depID, String postId, String staffName, String str);
}
