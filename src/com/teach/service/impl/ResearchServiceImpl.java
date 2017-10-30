package com.teach.service.impl;

import com.lanou.service.impl.BaseServiceImpl;
import com.lanou.util.PageBean;
import com.teach.dao.ResearchDao;
import com.teach.domain.Research;
import com.teach.service.ResearchService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
public class ResearchServiceImpl extends BaseServiceImpl<Research> implements ResearchService {

    private ResearchDao researchDao;

    private List<Object> param;

    public ResearchDao getResearchDao() {
        return researchDao;
    }

    public void setResearchDao(ResearchDao researchDao) {

        this.researchDao = researchDao;

        super.setBaseDao(researchDao);
    }

}
