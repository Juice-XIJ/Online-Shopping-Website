package com.product.showgoods.service;
/**
 * Created by XIJ on 4/7/2016.
 * showgoods
 * showComment
 */
import java.util.List;
import java.util.Map;


public interface ShowGoodsService {
    List<Map<String, Object>> showgoods(Object id);
    List<Map<String, Object>> showComment (Object id);
}
