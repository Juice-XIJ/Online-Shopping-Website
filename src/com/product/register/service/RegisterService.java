package com.product.register.service;
/**
 * Created by XIJ on 3/31/2016.
 * registerUser
 * @param users(email, username, passwords)
 * @return 1->register successfully; 0->fail
 */
import java.sql.SQLException;
import java.util.List;


public interface RegisterService {
	int registerUser(List<Object> params) throws SQLException;
}
