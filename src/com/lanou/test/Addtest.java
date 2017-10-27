package com.lanou.test;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.lanou.domain.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.Date;

/**
 * Created by dllo on 17/10/25.
 */
public class Addtest{

    private SessionFactory sessionFactory;

    @Before
    public void init(){

        Configuration configuration = new Configuration();

        configuration.configure();

        sessionFactory = configuration.buildSessionFactory();

    }

    @Test
    public void add(){

        Session session = sessionFactory.getCurrentSession();

        Staff staff = new Staff();

        staff.setLoginName("yayoi");

        staff.setLoginPwd("123456");

        staff.setOnDutyDate(new Date());

        staff.setStaffName("幼月");

        staff.setGender("女");

        Department department = new Department("扫大街部");

        session.save(department);

        Post post = new Post("扫大街主管",department);

        session.save(post);

        staff.setPost(post);

        session.save(staff);

    }

}
