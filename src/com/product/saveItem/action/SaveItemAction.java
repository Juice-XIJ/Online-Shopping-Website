package com.product.saveItem.action;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.saveItem.dao.SaveItemDao;
import com.product.saveItem.service.SaveItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by XIJ on 4/9/2016.
 */
@MultipartConfig
public class SaveItemAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SaveItemService service;
    /**
     * Constructor of the object.
     */
    public SaveItemAction() {
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
     * The copyImage method of the servlet. <br>
     *
     * This method is called when a doPost is working.
     *
     * @param request the request send by the client to the server
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     * @return return the relative path
     */

    private String copyImage (HttpServletRequest request) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        /*copy file to server*/
        Part part = request.getPart("image");
        Date now = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = dateFormat1.format(now);
        System.out.println(time);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
        String day = dateFormat2.format(now);
        System.out.println(day);
        String path = "D:/google drive/Courses/2016Spring/CS5200 32742 Database Management Sys SEC 01 - Spring 2016/project/Final/web/image/" + day + "/" + time + ".jpg";
        File outfile = new File(path);
        File ss = outfile.getParentFile();
        ss.mkdirs();
        part.write(path);
        /*End copy*/
        return "image/" + day + "/" + time + ".jpg";
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
        System.out.println("SaveItem init");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String path = request.getContextPath();

        Map map = (Map) request.getSession().getAttribute("map");

        /**
         * mapOfUserOrder: b.finish, g.id, g.nameOfGoods, b.dealPrice, b.dealDate, s.nameOfShop, i.path
         */
        Object seller = map.get("id");
        Object nameOfGoods = request.getParameter("nameOfGoods");
        Object price = request.getParameter("price");
        Object type = request.getParameter("option");
        Object description = request.getParameter("description");
        String relativePath = this.copyImage(request);
        System.out.println("Copy!");
        Object item_id = service.saveItem(nameOfGoods, seller, price, type, description);
        boolean flag = service.saveImage(relativePath, item_id);

        if(flag){
            System.out.println("Saved!");
            response.sendRedirect(path + "/myorder.jsp");
        }else{
            System.out.println("Could not save item!!");
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

        service = new SaveItemDao();
    }
}
