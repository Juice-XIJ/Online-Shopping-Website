package com.product.buy.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.buy.dao.BuyDao;
import com.product.buy.service.BuyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/7/2016.
 */
public class BuyAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BuyService service;
    /**
     * Constructor of the object.
     */
    public BuyAction() {
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
        System.out.println("Show Goods init");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();
        Object customer_id = request.getParameter("customer_id");
        Object goods_id = request.getParameter("goods_id");
        Object amount = request.getParameter("amount");
        Object price = request.getParameter("price");
        System.out.println("customer_id: "+customer_id);
        System.out.println("goods_id: "+goods_id);
        /**
         * goodsInfo: s.nameOfShop, g.nameOfGoods, g.price, g.description, i.path (List), g.id
         */
        boolean flag = service.buyGoods(customer_id, goods_id, amount, price);
        if(flag){
            response.sendRedirect(path + "/goods.jsp");
            System.out.println("Done!!");
        }else{
            System.out.println("Error!!");
            response.sendRedirect(path + "/goods.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here

        service = new BuyDao();
    }
}
