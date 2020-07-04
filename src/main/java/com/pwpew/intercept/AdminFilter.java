package com.pwpew.intercept;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter(urlPatterns = {"/bac/views/message.html","/bac/index.jsp","/bac/views/notice.html","/bac/views/post.html","/bac/views/volunteer.html","/bac/views/user.html"})
/*@WebFilter(filterName="AdminFilter",urlPatterns="/bac/views/message.html","/bac/index.jsp","/bac/views/*", initParams = {
		//value为不需要拦截的资源
		E:\idea\workspace\pwpswJarProject\web\bac\views\notice.html
@WebInitParam(name = "noLoginPaths", value = "")
})*/
/*/bac/index.jsp;*/
public class AdminFilter implements Filter {

    private FilterConfig fConfig;

    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse) response;
        HttpSession session = hrequest.getSession();
		/*hrequest.setCharacterEncoding("utf-8");
		hresponse.setContentType("text/html;charset=UTF-8");*/
        /*String noLoginPaths = fConfig.getInitParameter("noLoginPaths");



        if (noLoginPaths != null) {
            String[] strArray = noLoginPaths.split(";");
            for (int i = 0; i < strArray.length; i++) {

                if (strArray[i] == null || "".equals(strArray[i])) continue;

                if (hrequest.getRequestURI().indexOf(strArray[i]) != -1) {
                    chain.doFilter(request, response);
                    return;
                }
            }

        }*/

        if (session.getAttribute("adminName") != null) {
            chain.doFilter(hrequest, hresponse);
        } else {
            hresponse.sendRedirect("../error.jsp");
        }


    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.fConfig = fConfig;
    }

}
