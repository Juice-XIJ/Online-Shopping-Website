package com.product.newItem.dao;

import com.product.jdbc.dbutil.JdbcUtils;
import com.product.newItem.service.NewItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/10/2016.
 */
public class NewItemDao implements NewItemService {
    private JdbcUtils jdbcUtils;
    public NewItemDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> showNewItems(){
        List<Map<String, Object>> items = new ArrayList<>();
        JdbcUtils jdbcUtils = new JdbcUtils();
        String sql = "select g.*, if (r.rates is null, 0, r.rates) rates\n" +
                "from goods g\n" +
                "left join rate r \n" +
                "on (g.id = r.goods_id)\n" +
                "group by g.id\n" +
                "order by r.rates desc\n" +
                "limit ?";
        String sql1 = "select path from image where image.id = ? limit 1";
        try {
            jdbcUtils.getConnection();
            items = jdbcUtils.findResult(sql, 4);
            if (items != null) {
                for (Map<String, Object> aMap : items) {
                    List<Map<String, Object>> map1;
                    map1 = jdbcUtils.findResult(sql1, aMap.get("id"));
                    aMap.put("path", map1.get(0).get("path"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.releaseConn();
        }
        return items;
    }
}
