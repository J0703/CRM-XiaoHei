package com.lanou.interceptor;

import com.lanou.domain.Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by dllo on 17/10/30.
 */
public class RewriteInterceptor extends MethodFilterInterceptor{
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        Staff staffInfo = (Staff) ServletActionContext.getServletContext().getAttribute("staffInfo");

        String depID = staffInfo.getPost().getDepartment().getDepID();

        String pachyrhizusID = "2c9091865f589fa3015f58a11a3f0003";

        if (depID.equals(pachyrhizusID)) return actionInvocation.invoke();

        return "error";
    }
}
