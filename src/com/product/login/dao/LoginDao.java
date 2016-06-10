package com.product.login.dao;

/**
 * Created by XIJ on 3/31/2016.
 */

import java.util.*;

import com.product.jdbc.dbutil.JdbcUtils;
import com.product.login.service.LoginService;

public class LoginDao implements LoginService {
    private JdbcUtils jdbcUtils;
    public LoginDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> login(List<Object> params) {
        // TODO Auto-generated method stub
        String sql = "select id, email, username from users where email = ? and passwords = ?";
        String sql1 = "select id from admin where id = ?";
        String sql2 = "select id from customer where id = ?";
        String sql3 = "select isSeller from seller where id = ?";
        List<Map<String, Object>> map = new ArrayList<>();
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, params);
            if (!map.isEmpty()){
                int id =(int) map.get(0).get("id");

                List<Map<String, Object>> map1 = jdbcUtils.findResult(sql1, id);
                if (!map1.isEmpty())
                    map.get(0).put("admin", 1);
                else
                    map.get(0).put("admin", -1);

                List<Map<String, Object>> map2 = jdbcUtils.findResult(sql2, id);
                if (!map2.isEmpty())
                    map.get(0).put("customer", 1);
                else
                    map.get(0).put("customer", -1);

                List<Map<String, Object>> map3 = jdbcUtils.findResult(sql3, id);
                if (!map3.isEmpty())
                    map.get(0).put("seller", map3.get(0).get("isSeller"));
                else
                    map.get(0).put("seller", -1);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }

        return map;
    }
}
