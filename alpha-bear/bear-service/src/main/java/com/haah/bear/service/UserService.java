/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.service;

import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;

/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午4:12:25
 */
public interface UserService {

	public Result loginAuthenticate(UserPojo user);
	public UserPojo retrieveUserByUsername(String username);
	
}
