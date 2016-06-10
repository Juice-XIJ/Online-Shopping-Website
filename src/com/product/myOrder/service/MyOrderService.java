package com.product.myOrder.service;

import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/4/2016.
 */
public interface MyOrderService {
    List<Map<String, Object>> showOrders (Object email);
    List<Map<String, Object>> cancelOrder (Object customer, Object goods, Object dealDate, Object email);
}
