package com.product.jdbc.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class JdbcUtils {

	// Username of Database
	private final String USERNAME = "root";

	// Password of Database
	private final String PASSWORD = "xiejin";
	// Database driver
	private final String DRIVER = "com.mysql.jdbc.Driver";
	// URL
	private final String URL = "jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	//private final String URL = "jdbc:mysql:http://129.10.53.220:3306/project?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	// Connection of Database
	private Connection connection;
	// SQL query
	private PreparedStatement pstmt;
	// Result set
	private ResultSet resultSet;

	public JdbcUtils() {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Connect to the database
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
	}

	/**
	 * delete, update, insert with multiple parameters
	 * 
	 * @param sql sql
	 * @param params parameters of sql
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag;
		int result;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (Object param : params) {
				pstmt.setObject(index++, param);
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0;
		return flag;
	}

	/**
	 * delete, update, insert with one parameter
	 *
	 * @param sql sql
	 * @param param parameter of sql
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, Object param)
			throws SQLException {
		boolean flag;
		int result;
		pstmt = connection.prepareStatement(sql);
		pstmt.setObject(1, param);
		result = pstmt.executeUpdate();
		flag = result > 0;
		return flag;
	}

	/**
	 * select with multiple parameters
	 * 
	 * @param sql sql query
	 * @param params parameters
	 * @return List of result
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * select with one parameter
	 *
	 * @param sql sql query
	 * @param param parameter
	 * @return List
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findResult(String sql, Object param) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		pstmt.setObject(1, param);
		resultSet = pstmt.executeQuery();

		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * select with one parameter
	 *
	 * @param sql sql query
	 * @param param parameter
	 * @return List
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findSearchResult(String sql, Object param) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		pstmt.setObject(1,"%" + param + "%");
		pstmt.setObject(2,"%" + param + "%");
		resultSet = pstmt.executeQuery();

		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * select primary key (id with auto_increment)
	 *
	 * @return The primary key (id with auto_increment)
	 * @throws SQLException
	 */
	public Object getKey() throws SQLException{
		pstmt = connection.prepareStatement("select LAST_INSERT_ID()");
		resultSet = pstmt.executeQuery();
		resultSet.next();
		return resultSet.getInt(1);
	}

	/**
	 * close the JDBC connection
	 */

	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
