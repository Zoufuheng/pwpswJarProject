package com.pwpew.intercept;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Authintercept extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation arg0) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String name = arg0.getInvocationContext().getName();
        if ("verifyUser".equals(name)) {
            return arg0.invoke();
        } else if ("userRegister".equals(name)) {
            return arg0.invoke();
        } else if ("latestNotice".equals(name)) {
            return arg0.invoke();
        } else if ("showPostOfIndex".equals(name)) {
            return arg0.invoke();
        } else if ("showWantedInformationOfIndex".equals(name)) {
            return arg0.invoke();
        } else if ("volunteerRegister".equals(name)) {
            return arg0.invoke();
        } else if ("noticeList".equals(name)) {
            return arg0.invoke();
        } else if ("noticeMain".equals(name)) {
            return arg0.invoke();
        } else if ("vcode".equals(name)) {
            return arg0.invoke();
        } else if ("Lastnotice".equals(name)) {
            return arg0.invoke();
        } else if ("Nextnotice".equals(name)) {
            return arg0.invoke();
        } else if ("showPost".equals(name)) {
            return arg0.invoke();
        } else if ("showPostClass".equals(name)) {
            return arg0.invoke();
        } else if ("searchPost".equals(name)) {
            return arg0.invoke();
        } else if ("showDetailedInformation".equals(name)) {
            return arg0.invoke();
        } else if ("verifyAdmin".equals(name)) {
            return arg0.invoke();
        } else if ("showPostList".equals(name)) {
            return arg0.invoke();
        } else if ("modifyPasswordByEmail".equals(name)) {
            return arg0.invoke();
        } else if ("modifyPasswordByEmailNext".equals(name)) {
            return arg0.invoke();
        } else {
            String username = (String) session.getAttribute("username");
            String adminName = (String) session.getAttribute("adminName");
            if (username != null || adminName != null) {
                return arg0.invoke();
            } else {
                request.setAttribute("msg", "请先登陆");
                return "login";
            }
        }

    }


}
