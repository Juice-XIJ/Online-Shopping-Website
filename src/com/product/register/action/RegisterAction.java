package com.product.register.action;
/**
 * Created by XIJ on 3/31/2016.
 * Servlet Action
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.hibernate.validator.util.GetConstructor;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.product.register.dao.RegisterDao;
import com.product.register.service.RegisterService;

public class RegisterAction extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RegisterService service;

	/**
	 * Constructor of the object.
	 */
	public RegisterAction() {
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
		String path = request.getContextPath();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		System.out.println("email = " + email + "  username = " + username
				+" password = " + password);
		List<Object> params = new ArrayList<Object>();
		params.add(email);
		params.add(username);
		params.add(password);
		params.add(street);
		params.add(city);
		params.add(state);
		params.add(zipcode);
		int id = 0;
		try {
			id = service.registerUser(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(id!=0){
			System.out.println("Register Successfully!");
			Map<String, Object> map = new HashMap<>();
			map.put("email", email);
			map.put("username", username);
			map.put("admin", -1);
			map.put("customer", 1);
			map.put("seller", -1);
			map.put("id", id);
			request.getSession().setAttribute("map", map);
			System.out.println(map);
			response.sendRedirect(path + "/index.jsp");
		}else{
			System.out.println("fail to register");
			response.sendRedirect(path + "/signup.jsp?error=yes");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new RegisterDao(); 
	}

}
