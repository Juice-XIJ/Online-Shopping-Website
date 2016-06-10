package com.product.filter;

/**
 * Created by XIJ on 4/10/2016.
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

public class adminFilter implements Filter {
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
        /*System.out.println("path "+currentURL.substring(path.length()));*/

        if ((int)map.get("admin")!=1){
            /*System.out.println("Filtering!");*/
            httpServletResponse.sendRedirect(path + "/index.jsp");
            return;
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
}