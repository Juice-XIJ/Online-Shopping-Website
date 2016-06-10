package com.product.showType.service;

/**
 * Created by XIJ on 4/10/2016.
 * showType
 * @param type of item
 * @return List of items
 */
import java.util.List;
import java.util.Map;


public interface ShowTypeService {
    List<Map<String, Object>> showType(Object type);
}
