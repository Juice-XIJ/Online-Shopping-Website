package com.product.admin.dao;

import com.product.admin.service.AdminService;
import com.product.jdbc.dbutil.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/8/2016.
 */
public class AdminDao implements AdminService {
    private JdbcUtils jdbcUtils;
    public AdminDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> adminMessage(){
        List<Map<String, Object>> map = new ArrayList<>();
        String sql = "select u.id, u.email, u.username, s.nameOfShop\n" +
                "\tfrom users u, seller s\n" +
                "\t\twhere s.isSeller = ?\n" +
                "\t\t\tand s.id = u.id";
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, 0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }

        return map;
    }
    @Override
    public boolean approve(Object id){
        boolean flag=false;
        String sql = "update seller set isSeller = 1\n" +
                "\twhere seller.id = ?\n";
        try{
            jdbcUtils.getConnection();
            flag = jdbcUtils.updateByPreparedStatement(sql, id);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return flag;
    }
    @Override
    public boolean reject(Object id){
        boolean flag=false;
        String sql = "delete from seller \n" +
                "\twhere seller.id = ?";
        try{
            jdbcUtils.getConnection();
            flag = jdbcUtils.updateByPreparedStatement(sql, id);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return flag;
    }
}
