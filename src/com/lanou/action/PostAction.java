package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.service.DepartmentService;
import com.lanou.service.PostService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Controller("postAction")
@Scope("prototype")
public class PostAction extends ActionSupport implements ModelDriven<Post> {

    @Autowired
    @Qualifier("postService")
    private PostService postService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departService;

    private Post post;

    private List<Post> posts;

    private List<Department> departmentList;

    private String depID;

    public String findAllPost(){

        posts = postService.findAllPost();

        return SUCCESS;

    }

    public String intoEditPost(){

        departmentList = departService.findAllDepart();

        System.out.println(departmentList);

        if(post.getPostId() == null) return SUCCESS;


//        System.out.println(post.getPostId());

        this.post = postService.findPostById(post.getPostId());

        return SUCCESS;

    }

    public String addOrEditPost(){



        if(depID.equals("-1") || StringUtils.isBlank(post.getPostName())){

            departmentList = departService.findAllDepart();

            this.post = postService.findPostById(post.getPostId());

            addActionError("信息填写不完整,请填写完整后再提交");

            return INPUT;

        }

        Department depart = departService.findDepartById(depID);

        postService.addOrEditPost(post,depart);

        posts = postService.findAllPost();

        return SUCCESS;

    }

    @Override
    public Post getModel() {

        post = new Post();

        return post;

    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }
}
