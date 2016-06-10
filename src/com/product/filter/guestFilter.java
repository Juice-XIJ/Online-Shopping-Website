package com.product.filter;

/**
 * Created by XIJ on 3/31/2016.
 */

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class guestFilter implements Filter {
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        //过滤用户的请求，判断是否登录

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        String path = httpServletRequest.getContextPath();
        Map map = (Map) ((HttpServletRequest) request).getSession().getAttribute("map");
        String currentURL = httpServletRequest.getRequestURI();
        /*System.out.println("path "+currentURL.substring(path.length()));*/
        if (currentURL.substring(path.length()).equals("/") || currentURL.substring(path.length()).equals("/signup.jsp") ||currentURL.substring(path.length()).equals("/index.jsp")
                ||currentURL.substring(path.length()).equals("/signout.jsp")||currentURL.substring(path.length()).equals("/signin.jsp")){
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        else if (map == null){
            /*System.out.println("Filtering!");*/
            httpServletResponse.sendRedirect(path + "/signin.jsp");
            return;
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
}
