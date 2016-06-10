package com.product.sellerMessage.service;

import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/7/2016.
 */
public interface SellerMessageService {
    List<Map<String, Object>> sellerMessage (Object goods_id);
    boolean cancelOrder (Object status, Object username, Object goods, Object dealDate);
}
