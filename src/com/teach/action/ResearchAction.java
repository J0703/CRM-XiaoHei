package com.teach.action;

import com.lanou.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.teach.domain.Research;
import com.teach.service.ResearchService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */

@Controller("researchAction")
@Scope("prototype")
public class ResearchAction extends ActionSupport implements ModelDriven<Research>{

    private Research research;

    @Autowired
    @Qualifier("researchService")
    private ResearchService researchService;

    private int pageNum;

    private int pageSize = 3;

    @Override
    public Research getModel() {

        research = new Research();

        return research;
    }

}
