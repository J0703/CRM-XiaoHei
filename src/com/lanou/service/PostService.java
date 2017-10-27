package com.lanou.service;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.util.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface PostService extends BaseService<Post>{

    List<Post> findAllPost();

    Post findPostById(Serializable postId);

    void addOrEditPost(Post post, Department department);

    PageBean<Post> findAll(Post post, int pageNum, int pageSize);

}
