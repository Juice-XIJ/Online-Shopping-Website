package com.product.myOrder.dao;

import com.product.jdbc.dbutil.JdbcUtils;
import com.product.myOrder.service.MyOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/4/2016.
 */
public class MyOrderDao implements MyOrderService {
    private JdbcUtils jdbcUtils;
    public MyOrderDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> showOrders (Object email){
        List<Map<String, Object>> map = new ArrayList<>();
        String sql = "select b.finish, g.id, g.nameOfGoods, g.type, b.dealPrice, b.dealDate, b.amount, s.nameOfShop, if (r.rates is null, 0, r.rates) rates\n" +
                "from buy b join goods g join seller s join users u \n" +
                "\ton (b.customer = u.id\n" +
                "\tand u.email = ?\n" +
                "\tand b.goods = g.id\n" +
                "\tand g.seller = s.id)\n" +
                "\tleft join rate r\n" +
                "\ton (g.id = r.goods_id)";
        String sql1 = "select path from image where image.id = ? limit 1";
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
    @Override
    public List<Map<String, Object>> cancelOrder (Object customer, Object goods, Object dealDate, Object email){
        List<Object> params = new ArrayList<>();
        List<Map<String, Object>> map = new ArrayList<>();
        params.add(customer);
        params.add(goods);
        params.add(dealDate);
        String sql = "update buy set finish = 'Canceled' \n" +
                "\twhere buy.customer = ?\n" +
                "\t\tand buy.goods = ?\n" +
                "\t\tand buy.dealDate = ?";
        boolean flag;
        try{
            jdbcUtils.getConnection();
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
            if (flag){
                map = this.showOrders(email);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return map;
    }
}
