package com.product.search.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Juice on 2016/4/20.
 */
public interface SearchService {
    List<Map<String, Object>> searchResult(Object keyword);
}
