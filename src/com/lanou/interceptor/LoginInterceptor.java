package com.lanou.interceptor;

import com.lanou.domain.Staff;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by dllo on 17/10/27.
 */
public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {

        Staff staffInfo = (Staff) ServletActionContext.getServletContext().getAttribute("staffInfo");

        if(staffInfo == null) return "login";

        return actionInvocation.invoke();
    }
}
