package com.lanou.service.impl;

import com.lanou.dao.StaffDao;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.StaffService;
import com.lanou.util.PageBean;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService {

    private StaffDao staffDao;

    private List<String> params;

    @Override
    public Staff login(String loginName, String loginPwd) {

        String hql = "from Staff where loginName=? and loginPwd=?";

        Object[] params = {loginName, loginPwd};

        return staffDao.findSingle(hql, params);

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
    public PageBean<Staff> findAllStaff(Staff staff, int pageNum, int pageSize) {

        if(pageNum == 0) pageNum ++;

        String hql = "select count(s) from Staff s";

        String condition = "from Staff";

        return super.findAll(staff,pageNum,pageSize,hql,condition);
    }

    @Override
    public PageBean<Staff> findQuery(Staff staff, int pageNum, int pageSize, Map<String,String> param) {

        if(pageNum == 0) pageNum ++;

        String condition = "from Staff s where 1=1 ";

        condition = advancedQuery(param.get("depID"),param.get("postId"),param.get("staffName"),condition);

        String hql = "select count(s) "+ condition;

        int totalRecord = staffDao.getTotalRecord(hql, params.toArray());

        PageBean<Staff> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);

        List<Staff> data = staffDao.findAll(condition, params.toArray(), pageBean.getStartIndex(), pageBean.getPageSize());

        pageBean.setData(data);

        return pageBean;
    }

    @Override
    public String advancedQuery(String depID, String postId, String staffName, String str) {

        params = new ArrayList<>();

        StringBuffer sb = new StringBuffer(str);

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

        return sb.toString();

    }





    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {

        this.staffDao = staffDao;

        super.setBaseDao(staffDao);
    }
}
