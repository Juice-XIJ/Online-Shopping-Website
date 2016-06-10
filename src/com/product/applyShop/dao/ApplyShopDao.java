package com.product.applyShop.dao;

import com.product.applyShop.service.ApplyShopService;
import com.product.jdbc.dbutil.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/7/2016.
 */
public class ApplyShopDao implements ApplyShopService {
    private JdbcUtils jdbcUtils;
    public ApplyShopDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public boolean applyShop(Object email, Object nameOfShop){
        String sql="insert into seller (id, isSeller, nameOfShop) values ((select id from users where users.email = ?), ?, ?);";
        List<Object> params = new ArrayList<>();
        params.add(email);
        params.add("0");
        params.add(nameOfShop);
        boolean flag = false;
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
    @Override
    public List<Map<String, Object>> showGoods (String email){
        String sql="select nameOfGoods, price, description, g.id, nameOfShop, g.type, if (r.rates is null, 0, r.rates) rates\n" +
                "\tfrom goods g join users u join seller s \n" +
                "\ton (u.email = ? and g.seller = u.id and s.id = u.id)\n" +
                "\tleft join rate r\n" +
                "\t\ton (g.id = r.goods_id);";
        String sql1 = "select path from image where image.id = ? limit 1";
        List<Map<String, Object>> map = new ArrayList<>();
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, email);
            if (map==null)
                return null;
            for (Map<String, Object> aMap : map) {
                List<Map<String, Object>> map1;
                map1 = jdbcUtils.findResult(sql1, aMap.get("id"));
                aMap.put("path", map1.get(0).get("path"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return map;
    }
}
