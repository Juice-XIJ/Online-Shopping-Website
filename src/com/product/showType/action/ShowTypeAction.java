package com.product.showType.action;
/**
 * Created by XIJ on 4/10/2016.
 * Servlet Action
 */
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.showType.dao.ShowTypeDao;
import com.product.showType.service.ShowTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;


public class ShowTypeAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShowTypeService service;
    /**
     * Constructor of the object.
     */
    public ShowTypeAction() {
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
        System.out.println("Find Type....");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();

        Object type = request.getParameter("type");
        System.out.println("type: "+type);
        List<Map<String, Object>> allItemOfType = service.showType(type);
        /**
         * allItemOfType: g.id, g.nameOfGoods, g.seller, g.price, g.type, g.description, i.path
         */
        if(allItemOfType!=null){
            request.getSession().setAttribute("type", type);
            request.getSession().setAttribute("allItemOfType", allItemOfType);
            System.out.println("allItemOfType: "+allItemOfType);
            response.sendRedirect(path + "/type.jsp");
        }else{
            request.getSession().setAttribute("allItemOfType", null);
            System.out.println("No Item in this Type!!");
            response.sendRedirect(path + "/type.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here

        service = new ShowTypeDao();
    }
}
