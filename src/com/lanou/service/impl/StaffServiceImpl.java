package com.lanou.service.impl;

import com.lanou.dao.StaffDao;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.StaffService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;

    @Override
    public Staff login(String loginName, String loginPwd) {

        String hql = "from Staff where loginName=? and loginPwd=?";

        Object[] params = {loginName, loginPwd};

        return staffDao.findSingle(hql, params);

    }

    @Override
    public List<Staff> findAllStaff() {

        String hql = "from Staff";

        return staffDao.findAll(hql);
    }

    @Override
    public Staff findStaffById(Serializable id) {

        return staffDao.findById(id, Staff.class);

    }

    @Override
    public void addStaff(Post post, Staff staff) {

        staff.setPost(post);

        if(StringUtils.isBlank(staff.getStaffId())){

            staffDao.add(staff);

        } else{

            staffDao.update(staff);

        }

    }

    @Override
    public List<Staff> advancedQuery(String depID, String postId, String staffName) {

        List<String> params = new ArrayList<>();

        StringBuffer sb = new StringBuffer("from Staff s where 1=1 ");

        if (!depID.equals("-1")){

            sb.append("and s.post.department.depID=? ");

            params.add(depID);

        }

        if(!postId.equals("-1")){

            sb.append("and s.post.postId=? ");

            params.add(postId);

        }

        if(!StringUtils.isBlank(staffName)){

            sb.append("and s.staffName like ?");

            params.add("%" + staffName.trim() + "%");

        }

        return staffDao.find(sb.toString(), params.toArray());

    }

    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }
}
