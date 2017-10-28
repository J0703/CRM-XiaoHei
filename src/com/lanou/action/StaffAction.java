package com.lanou.action;

import com.lanou.dao.StaffDao;
import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import com.lanou.service.DepartmentService;
import com.lanou.service.PostService;
import com.lanou.service.StaffException;
import com.lanou.service.StaffService;
import com.lanou.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by dllo on 17/10/25.
 */

@Controller("StaffAction")
@Scope("prototype")
public class StaffAction extends ActionSupport implements ModelDriven<Staff>{

    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departService;

    @Autowired
    @Qualifier("postService")
    private PostService postService;

    private Staff staff;

    private List<Staff> staffs;

    private List<Department> departmentList;

    private String depID;

    private String postId;

    private Set<Post> posts;

    private int pageNum;

    private Map<String,String> maps;

    /* 每页的数据条数 */
    private int pageSize = 3;

    private String oldPassword;

    private String newPassword;

    private String reNewPassword;

    public String login(){

        Staff staffInfo = staffService.login(this.staff.getLoginName(), this.staff.getLoginPwd());

        if(staffInfo != null){

            ActionContext.getContext().getApplication().put("staffInfo",staffInfo);

            return SUCCESS;

        } else {

            addActionError("用户名或密码错误!");

        }

        return INPUT;

    }

    public String logout(){

        ActionContext.getContext().getApplication().remove("staffInfo");

        return SUCCESS;

    }

    /* 更改密码 */
    public String changePassword(){

        Staff staff = (Staff) ActionContext.getContext().getApplication().get("staffInfo");

        staff.setLoginPwd(newPassword);

        try {

            staffService.addStaff(staff.getPost(),staff);

            return SUCCESS;

        } catch (StaffException e) {

            addActionError(e.getMessage());

            return INPUT;

        }
    }

    public String findAll(){

        PageBean<Staff> pageBean = staffService.findAllStaff(staff, pageNum, pageSize);

        ActionContext.getContext().put("pageBean", pageBean);

        return SUCCESS;

    }

    public String findAllDepartAjax(){

        this.departmentList = departService.findAllDepart();

        return SUCCESS;

    }

    public String findPostByDepatId(){

        if(depID.equals("-1")){

            posts = new HashSet<>();

            return SUCCESS;

        }

        posts = departService.findDepartById(depID).getPosts();

        System.out.println(posts);

        return SUCCESS;
    }

    /* 修改与添加共用方法 */
    public String addStaff(){

        List<String> msgs = passForm();

        if(!msgs.isEmpty()){

            for (String msg : msgs) {

                addActionError(msg);

            }

            /* 表单回显方法 */
            findInfoFromStaffId();

            return INPUT;
        }

        Post post = postService.findPostById(postId);

        //禁止注册重复的登录名,不允许修改重复的登录名,本身登录名可以通过修改
        try {

            staffService.addStaff(post,staff);

            PageBean<Staff> pageBean = staffService.findAllStaff(staff, pageNum, pageSize);

            ActionContext.getContext().put("pageBean", pageBean);

            return SUCCESS;

        } catch (StaffException e) {

            addActionError(e.getMessage());

            /* 表单回显方法 */
            findInfoFromStaffId();

            return INPUT;

        }

    }

    /* 进入修改员工页面方法,进行表单回显 */
    public String intoEditStaff(){

        /* 表单回显方法 */
        findInfoFromStaffId();

        return SUCCESS;

    }

    /* 高级查询 */
    public String advancedQuery(){

        maps = new HashMap<>();

        maps.put("depID",depID);

        maps.put("postId",postId);

        maps.put("staffName",staff.getStaffName());

        PageBean<Staff> pageBean = staffService.findQuery(staff, pageNum, pageSize, maps);

        ActionContext.getContext().put("pageBean", pageBean);

        return SUCCESS;

    }

    /* 表单回显封装方法 */
    private void findInfoFromStaffId(){

        this.staff = staffService.findStaffById(staff.getStaffId());

        this.departmentList = departService.findAllDepart();

        this.posts = staff.getPost().getDepartment().getPosts();

    }


    @Override
    public Staff getModel() {

        staff = new Staff();

        return staff;
    }

    /* 登录验证拦截方法 */
    public void validateLogin(){

        if(StringUtils.isBlank(staff.getLoginName()) || StringUtils.isBlank(staff.getLoginPwd())){

            addActionError("用户名或密码不能为空,请重新输入!");

        }

    }

    /* 更改密码拦截验证 */
    public void validateChangePassword(){

        Staff staff = (Staff) ActionContext.getContext().getApplication().get("staffInfo");

         /* 刷新状态 */
        staff = staffService.findStaffById(staff.getStaffId());

        if(!staff.getLoginPwd().equals(oldPassword)){

            addActionError("连旧密码都不知道?真怀疑你是怎么登陆上来的");

            return;

        } else if(StringUtils.isBlank(newPassword) || StringUtils.isBlank(reNewPassword)){

            addActionError("难道敲回车就能登录了么 o_O???");

            return;

        } else if(!newPassword.equals(reNewPassword)){

            addActionError("两次输入的并不一样‘(*>﹏<*)′ ~");

            return;

        } else if(oldPassword.equals(newPassword)){

            addActionError("这样改密码还有什么用 π_π");

        }

    }

    /* 表单判空方法 */
    private List<String> passForm(){

        List<String> msgs = new ArrayList<>();

        if(postId.equals("-1")) msgs.add("难道这个员工不属于这个单位?");

        if(StringUtils.isBlank(staff.getLoginName())) msgs.add("你拿什么登录啊?亲!");

        if(StringUtils.isBlank(staff.getLoginPwd())) msgs.add("密码怎么不见了?");

        if(StringUtils.isBlank(staff.getStaffName())) msgs.add("可能这个人是个没名字的黑户");

        if(staff.getOnDutyDate() == null) msgs.add("难到这个人的入职时间超过了N个世纪");

        return msgs;

    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }
}
