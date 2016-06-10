package com.product.saveItem.dao;
/**
 * Created by XIJ on 4/9/2016.
 * implement service
 */
import com.product.jdbc.dbutil.JdbcUtils;
import com.product.saveItem.service.SaveItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SaveItemDao implements SaveItemService {
    private JdbcUtils jdbcUtils;
    public SaveItemDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public Object saveItem(Object nameOfGoods, Object seller, Object price, Object type, Object description){
        String sql = "insert into goods (nameOfGoods, seller, price, type, description) values (?,?,?,?,?);";
        List<Object> params = new ArrayList<>();
        params.add(nameOfGoods);
        params.add(seller);
        params.add(price);
        params.add(type);
        params.add(description);
        boolean flag;
        Object id = new Object();
        try{
            jdbcUtils.getConnection();
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
            if (flag){
                id = jdbcUtils.getKey();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return id;
    }

    public boolean saveImage(String relativePath, Object item_id){
        String sql = "insert into image (id, path) values (?, ?);";
        List<Object> params = new ArrayList<>();
        params.add(item_id);
        params.add(relativePath);
        boolean flag=false;
        try{
            jdbcUtils.getConnection();
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return flag;
    }
}
