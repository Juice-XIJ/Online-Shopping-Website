package com.product.saveItem.service;


/**
 * Created by XIJ on 4/9/2016.
 */
public interface SaveItemService {
    Object saveItem(Object nameOfGood, Object seller, Object price, Object type, Object description);
    boolean saveImage(String relativePath, Object item_id);
}
