/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.client.fallback;

import org.springframework.web.bind.annotation.RequestParam;

import com.haah.bear.api.client.UserServiceClient;
import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;

public class UserServiceClientFallback implements UserServiceClient {

	@Override
	public Result authenticate(UserPojo user) {
		return Result.error("Can't validate the user of this login");
	}
	
	@Override
	public UserPojo retrieveUserByUsername(@RequestParam("username")String username) {
		return new UserPojo();
	}
}
