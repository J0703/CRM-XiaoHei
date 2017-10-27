package com.lanou.service.impl;

import com.lanou.dao.PostDao;
import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.service.PostService;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;


/**
 * Created by dllo on 17/10/25.
 */
public class PostServiceImpl implements PostService {

    private PostDao postDao;

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> findAllPost() {

        String hql = "from Post";

        return postDao.findAll(hql);

    }

    @Override
    public Post findPostById(Serializable postId) {

        return postDao.findById(postId,Post.class);

    }

    @Override
    public void addOrEditPost(Post post, Department department) {

        post.setDepartment(department);

        if(StringUtils.isBlank(post.getPostId())){

            postDao.add(post);

        }else {

            postDao.update(post);

        }

    }
}
