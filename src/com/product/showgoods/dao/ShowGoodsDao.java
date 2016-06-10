package com.product.showgoods.dao;
/**
 * Created by XIJ on 4/7/2016.
 * implement service
 */
import com.product.jdbc.dbutil.JdbcUtils;
import com.product.showgoods.service.ShowGoodsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ShowGoodsDao implements ShowGoodsService {
    private JdbcUtils jdbcUtils;
    public ShowGoodsDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> showgoods(Object id){
        List<Map<String, Object>> map = new ArrayList<>();
        String sql = "select s.nameOfShop, g.nameOfGoods, g.price, g.type, g.description, if (r.rates is null, 0, r.rates) rates \n" +
                "\tfrom seller s, goods g\n" +
                "\t\tleft join rate r on (g.id = r.goods_id)\n" +
                "\t\t\twhere g.seller = s.id\n" +
                "\t\t\t\tand g.id = ?;";
        String sql1 = "select path from image where image.id = ?";
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, id);
            if (map==null)
                return null;
            for (Map<String, Object> aMap : map) {
                List<Map<String, Object>> map1;
                map1 = jdbcUtils.findResult(sql1, id);
                aMap.put("path", map1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return map;
    }
    @Override
    public List<Map<String, Object>> showComment (Object id){
        List<Map<String, Object>> map = new ArrayList<>();
        String sql = "select b.commentOfGoods from buy b\n" +
                "\twhere b.goods = ?";
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, id);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return map;
    }
}
