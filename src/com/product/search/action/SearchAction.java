package com.product.search.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.search.dao.SearchDao;
import com.product.search.service.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juice on 2016/4/20.
 */
public class SearchAction extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SearchService service;

    /**
     * Constructor of the object.
     */
    public SearchAction() {
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
        System.out.println("Find Search Items!!!");
        String path = request.getContextPath();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        Object keyword = request.getParameter("search");
        List<Map<String, Object>> items= service.searchResult(keyword);
        System.out.println("items: "+items);
        if(items!=null){
            System.out.println("Search Items!!!");
            request.getSession().setAttribute("items", items);
            response.sendRedirect(path + "/search.jsp");
        }else{
            items = new ArrayList<>();
            request.getSession().setAttribute("items", items);
            System.out.println("No Items....");
            response.sendRedirect(path + "/search.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
        service = new SearchDao();
    }
}
