package com.product.sellerMessage.dao;
/**
 * Created by XIJ on 4/7/2016.
 * implement service
 */
import com.product.jdbc.dbutil.JdbcUtils;
import com.product.sellerMessage.service.SellerMessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SellerMessageDao implements SellerMessageService {
    private JdbcUtils jdbcUtils;
    public SellerMessageDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> sellerMessage (Object goods_id){
        List<Map<String, Object>> map = new ArrayList<>();
        String sql = "select b.finish, b.dealPrice, b.dealDate, b.commentOfGoods, u.username, if (r.rates is null, 0, r.rates) rates\n" +
                "\tfrom buy b join users u join goods g on (b.goods = ?\n" +
                "\t\t\tand b.customer = u.id and b.goods = g.id)\n" +
                "\tleft join rate r\n" +
                "\ton (g.id = r.goods_id);";
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, goods_id);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return map;
    }

    @Override
    public boolean cancelOrder (Object status, Object username, Object goods, Object dealDate){
        boolean flag = false;
        List<Object> params = new ArrayList<>();
        params.add(status);
        params.add(username);
        params.add(goods);
        params.add(dealDate);
        String sql = "update buy set finish = ? \n" +
                "\twhere buy.customer = \n" +
                "    (select id from users where users.username = ?)\n" +
                "\t\tand buy.goods = ?\n" +
                "\t\tand buy.dealDate = ?";
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
