package com.product.admin.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.admin.dao.AdminDao;
import com.product.admin.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by XIJ on 4/8/2016.
 */
public class AdminAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminService service;
    /**
     * Constructor of the object.
     */
    public AdminAction() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        if (DriverManager.getDrivers().nextElement()!=null){
            try{
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Put your code here
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
        System.out.println("Seller Message init");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();


        Object status = request.getParameter("status");
        Object id = request.getParameter("id");
        if (status!=null){
            if (Objects.equals(status.toString(), "1")) {
                service.approve(id);
            }else{
                service.reject(id);
            }
        }
        List<Map<String, Object>> adminMessage = service.adminMessage();
        /**
         * adminMessage: u.id, u.email, u.username, s.nameOfShop
         */
        if(adminMessage!=null){
            request.getSession().setAttribute("adminMessage", adminMessage);
            System.out.println("adminMessage: "+adminMessage);
            response.sendRedirect(path + "/admin.jsp");
        }else{
            request.getSession().setAttribute("adminMessage", null);
            System.out.println("No Admin messages!!");
            response.sendRedirect(path + "/admin.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here

        service = new AdminDao();
    }
}
