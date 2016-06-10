package com.product.applyShop.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.applyShop.dao.ApplyShopDao;
import com.product.applyShop.service.ApplyShopService;

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
public class ApplyShopAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ApplyShopService service;
    /**
     * Constructor of the object.
     */
    public ApplyShopAction() {
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
        System.out.println("Apply Shop init");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();

        Map map = (Map)request.getSession().getAttribute("map");
        String email = (String)map.get("email");
        int isSeller = (int)map.get("seller");
        if (isSeller!=1){
            String nameOfShop = request.getParameter("shopname");
            if (nameOfShop==null){
                response.sendRedirect(path + "/mygoods.jsp");
                return;
            }
            boolean flag = service.applyShop(email, nameOfShop);
            if(flag){
                map.put("seller", 0);
                request.getSession().setAttribute("map", map);
                System.out.println("New map: "+map);
                response.sendRedirect(path + "/mygoods.jsp");
            }else{
                System.out.println("Error!!");
                response.sendRedirect(path + "/mygoods.jsp");
            }
        }else{
            /**
             * goodsOfSeller: nameOfGoods, price, description, g.id, nameOfShop, i.path
             */
            System.out.println("Find goods...");
            List<Map<String, Object>> goodsOfSeller = service.showGoods(email);
            if(!goodsOfSeller.isEmpty()){
                request.getSession().setAttribute("goodsOfSeller", goodsOfSeller);
                System.out.println("goodsOfSeller: "+goodsOfSeller);
                response.sendRedirect(path + "/mygoods.jsp");
            }else{
                request.getSession().setAttribute("goodsOfSeller", null);
                System.out.println("goodsOfSeller is Empty!!");
                response.sendRedirect(path + "/mygoods.jsp");
            }
        }

    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here

        service = new ApplyShopDao();
    }
}
