package com.product.login.service;

/**
 * Created by XIJ on 3/31/2016.
 */
import java.util.List;
import java.util.Map;

public interface LoginService {
    List<Map<String, Object>> login(List<Object> params);
}
