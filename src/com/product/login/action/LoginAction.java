package com.product.login.action;

/**
 * Created by XIJ on 3/31/2016.
 */

import java.io.IOException;
import java.sql.DriverManager;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.login.dao.LoginDao;
import com.product.login.service.LoginService;

public class LoginAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService service;
    /**
     * Constructor of the object.
     */
    public LoginAction() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy();
        // Kill the JDBC DRIVER
        if (DriverManager.getDrivers().nextElement()!=null){
            try{
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        // Kill the threads
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Login....");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<Object> params = new ArrayList<Object>();
        params.add(email);
        params.add(password);
        /**
         * map: id, email, username, admin, customer, seller
         */
        List<Map<String, Object>> map = service.login(params);
        if(!map.isEmpty()){
            request.getSession().setAttribute("map", map.get(0));
            System.out.println("map = "+map);
            response.sendRedirect(path + "/index.jsp");
        }else{
            System.out.println("fail to login");
            response.sendRedirect(path + "/index.jsp?error=yes");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
        service = new LoginDao();
    }
}
