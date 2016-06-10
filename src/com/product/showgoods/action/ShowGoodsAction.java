package com.product.showgoods.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.showgoods.dao.ShowGoodsDao;
import com.product.showgoods.service.ShowGoodsService;

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
public class ShowGoodsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShowGoodsService service;
    /**
     * Constructor of the object.
     */
    public ShowGoodsAction() {
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
        String id = request.getParameter("good");
        System.out.println("id: "+id);
        /**
         * goodsInfo: s.nameOfShop, g.nameOfGoods, g.id, g.price, g.type, g.description, i.path (List)
         */
        List<Map<String, Object>> goodsInfo = service.showgoods(id);
        List<Map<String, Object>> commentOfGoods = service.showComment(id);
        if(!goodsInfo.isEmpty()){
            goodsInfo.get(0).put("id", id);
            request.getSession().setAttribute("goodsInfo", goodsInfo.get(0));
            request.getSession().setAttribute("commentOfGoods", commentOfGoods);
            System.out.println("goodsInfo: "+goodsInfo);
            System.out.println("commentOfGoods: "+commentOfGoods);
            response.sendRedirect(path + "/goods.jsp");
        }else{
            System.out.println("Error!!");
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

        service = new ShowGoodsDao();
    }
}
