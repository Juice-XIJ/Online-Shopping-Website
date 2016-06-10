package com.product.admin.service;

import java.util.List;
import java.util.Map;

/**
 * Created by XIJ on 4/8/2016.
 */
public interface AdminService {
    List<Map<String, Object>> adminMessage();
    boolean approve(Object id);
    boolean reject(Object id);
}
