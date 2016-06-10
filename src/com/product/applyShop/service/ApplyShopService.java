package com.product.applyShop.service;

import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/7/2016.
 */
public interface ApplyShopService {
    boolean applyShop(Object email, Object nameOfShop);
    List<Map<String, Object>> showGoods (String email);
}
