package com.product.register.dao;
/**
 * Created by XIJ on 3/31/2016.
 * Implement the service
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import com.product.jdbc.dbutil.JdbcUtils;
import com.product.register.service.RegisterService;

public class RegisterDao implements RegisterService {
	private JdbcUtils jdbcUtils = null;
	public RegisterDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}
	@Override
	public int registerUser(List<Object> params) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		jdbcUtils.getConnection();
		String sql = "insert into users (email, username, passwords, street, city, state, zipcode) values (?, ?, ?,?,?,?,?)";
		String sql1 = "insert into customer(id) values ((select id from users where email = ?))";
		String sql2 = "select id from users where email = ?";
		int id=0;
		List<Map<String, Object>> map;
		try{
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
			if (flag){
				flag = jdbcUtils.updateByPreparedStatement(sql1, params.get(0));
				map = jdbcUtils.findResult(sql2, params.get(0));
				id = (int) map.get(0).get("id");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			jdbcUtils.releaseConn();
		}
		return !flag ?0:id;
	}

}
