package com.product.search.dao;

import com.product.jdbc.dbutil.JdbcUtils;
import com.product.search.service.SearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juice on 2016/4/20.
 */
public class SearchDao implements SearchService {
    private JdbcUtils jdbcUtils;
    public SearchDao(){
        jdbcUtils = new JdbcUtils();
    }
    @Override
    public List<Map<String, Object>> searchResult(Object keyword){
        List<Map<String, Object>> items = new ArrayList<>();
        JdbcUtils jdbcUtils = new JdbcUtils();
        String sql = "select g.*, if (r.rates is null, 0, r.rates) rates\n" +
                "from goods g\n" +
                "left join rate r \n" +
                "on (g.id = r.goods_id)\n" +
                "where g.nameOfGoods like ?\n" +
                "or g.description like ?\n" +
                "group by g.id\n" +
                "order by r.rates desc";
        String sql1 = "select path from image where image.id = ? limit 1";
        try {
            jdbcUtils.getConnection();
            items = jdbcUtils.findSearchResult(sql, keyword);
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
