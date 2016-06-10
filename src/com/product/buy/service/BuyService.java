package com.product.buy.service;

import java.util.Objects;

/**
 * Created by XIJ on 4/7/2016.
 */
public interface BuyService {
    boolean buyGoods(Object customer_id, Object goods_id, Object amount, Object price);
}
