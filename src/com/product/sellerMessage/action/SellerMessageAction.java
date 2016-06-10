package com.product.sellerMessage.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.sellerMessage.dao.SellerMessageDao;
import com.product.sellerMessage.service.SellerMessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by XIJ on 4/8/2016.
 */
public class SellerMessageAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SellerMessageService service;
    /**
     * Constructor of the object.
     */
    public SellerMessageAction() {
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

        List<Map<String, Object>> goodsOfSeller = (List<Map<String, Object>>) request.getSession().getAttribute("goodsOfSeller");

        System.out.println("Message, goodsOfSeller: "+ goodsOfSeller);
        /**
         * goodsOfSeller: nameOfGoods, price, description, g.id, nameOfShop, i.path
         */
        /**
         * sellerMessage: b.finish, b.dealPrice, b.dealDate, u.username
         */
        Object status = request.getParameter("status");
        Object username = request.getParameter("username");
        Object good_id = request.getParameter("good_id");
        Object dealDate = request.getParameter("dealDate");
        boolean flag;
        if (status!=null){
            flag = service.cancelOrder(status, username, good_id, dealDate);
            if (flag){
                System.out.println(status+" Operation successfully");
            }else{
                System.out.println(status+" Operation Failed");
            }
        }

        List<List> sellerMessage = new ArrayList<>();
        if(goodsOfSeller!=null){
            sellerMessage.addAll(goodsOfSeller.stream().map(aMap -> service.sellerMessage(aMap.get("id"))).collect(Collectors.toList()));
            request.getSession().setAttribute("sellerMessage", sellerMessage);
            System.out.println("sellerMessage: "+sellerMessage);
            response.sendRedirect(path + "/message.jsp");
        }else{
            request.getSession().setAttribute("sellerMessage", null);
            System.out.println("Empty!!");
            response.sendRedirect(path + "/message.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here

        service = new SellerMessageDao();
    }
}
