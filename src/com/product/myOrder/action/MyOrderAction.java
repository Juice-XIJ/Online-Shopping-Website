package com.product.myOrder.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.myOrder.dao.MyOrderDao;
import com.product.myOrder.service.MyOrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/4/2016.
 */
public class MyOrderAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MyOrderService service;
    /**
     * Constructor of the object.
     */
    public MyOrderAction() {
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
        System.out.println("MyOrder init");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();
        /*int a = Integer.valueOf(id).intValue();*/
        Map map = (Map) request.getSession().getAttribute("map");
        Object email = map.get("email");
        System.out.println("email: "+ email);
        /**
         * mapOfUserOrder: b.finish, g.id, g.nameOfGoods, g.type, b.dealPrice, b.dealDate, s.nameOfShop, i.path
         */
        Object cancel = request.getParameter("cancel");
        Object dealDate = request.getParameter("dealDate");

        List<Map<String, Object>> mapOfUserOrder;
        if (cancel==null){
            mapOfUserOrder = service.showOrders(email);
        }else{
            System.out.println("cancel: "+ cancel);
            System.out.println("dealDate: "+ dealDate);
            mapOfUserOrder = service.cancelOrder(map.get("id"), cancel, dealDate, email);
        }

        if(!mapOfUserOrder.isEmpty()){
            request.getSession().setAttribute("mapOfUserOrder", mapOfUserOrder);
            System.out.println("mapOfUserOrder: "+mapOfUserOrder);
            response.sendRedirect(path + "/myorder.jsp");
        }else{
            request.getSession().setAttribute("mapOfUserOrder", null);
            System.out.println("Empty!!");
            response.sendRedirect(path + "/myorder.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here

        service = new MyOrderDao();
    }
}
