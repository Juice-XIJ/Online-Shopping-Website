package com.product.showType.dao;
/**
 * Created by XIJ on 4/10/2016.
 * implement service
 */
import com.product.jdbc.dbutil.JdbcUtils;
import com.product.showType.service.ShowTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ShowTypeDao implements ShowTypeService {
    private JdbcUtils jdbcUtils;
    public ShowTypeDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> showType(Object type){
        List<Map<String, Object>> map = new ArrayList<>();
        String sql = "select g.*, if (r.rates is null, 0, r.rates) rates\n" +
                "from goods g\n" +
                "left join rate r \n" +
                "on (g.id = r.goods_id)\n" +
                "where g.type = ?\n" +
                "group by g.id\n" +
                "order by r.rates desc";
        String sql1 = "select path from image where image.id = ? limit 1";
        try{
            jdbcUtils.getConnection();
            map = jdbcUtils.findResult(sql, type);
            if (map != null) {
                for (Map<String, Object> aMap : map) {
                    List<Map<String, Object>> map1;
                    map1 = jdbcUtils.findResult(sql1, aMap.get("id"));
                    aMap.put("path", map1.get(0).get("path"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            jdbcUtils.releaseConn();
        }
        return map;
    }
}
