package com.haah.bear.mapper;

import com.haah.bear.core.model.db.UserDb;

public interface UserMapper {
	
	UserDb retrieveUserByUsername(String username);
	
	int authenticate(UserDb user);

}
