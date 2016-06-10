package com.product.buy.dao;

import com.product.buy.service.BuyService;
import com.product.jdbc.dbutil.JdbcUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by XIJ on 4/7/2016.
 */
public class BuyDao implements BuyService{
    private JdbcUtils jdbcUtils;
    public BuyDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public boolean buyGoods(Object customer_id, Object goods_id, Object amount, Object price){

        String sql = "insert into buy (customer, goods, dealDate, amount, dealPrice, finish) values (?, ?, ?, ?, ?, 'pending')";
        List<Object> params = new ArrayList<>();
        params.add(customer_id);
        params.add(goods_id);
        Date now = new Date();
        params.add(now);
        params.add(amount);
        params.add(price);
        boolean flag = false;
        try{
            jdbcUtils.getConnection();
            flag = jdbcUtils.updateByPreparedStatement(sql,params);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return flag;
    }
}
