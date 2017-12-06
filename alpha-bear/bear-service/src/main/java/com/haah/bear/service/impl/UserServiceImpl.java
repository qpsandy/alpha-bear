/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haah.bear.constants.BizCode;
import com.haah.bear.core.model.db.UserDb;
import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;
import com.haah.bear.mapper.UserMapper;
import com.haah.bear.service.UserService;


/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午4:46:02
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 登录验证用户名密码
	 * @param user
	 * @return
	 */
	@Override
	public Result loginAuthenticate(UserPojo user){
		logger.info("Login---username:"+user.getUsername()+", password:"+ user.getPassword());
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			logger.error(BizCode.USER_PASSWORD_NULL.getMessage());
			return Result.error(BizCode.USER_PASSWORD_NULL.getMessage());
		}
		UserDb dbUser = userMapper.retrieveUserByUsername(user.getUsername());
		if (dbUser == null) {
			logger.error(BizCode.USER_NOT_EXIST.getMessage());
			return Result.error(BizCode.USER_NOT_EXIST.getMessage());
		}
		if (!this.authenticate(new UserDb(user.getUsername(), user.getPassword()))){
			logger.error(BizCode.USER_WRONG_PASSWORD.getMessage());
			return Result.error(String.format(BizCode.USER_WRONG_PASSWORD.getMessage()));
		}
		return Result.success(dbUser);
	}
	
	public UserPojo retrieveUserByUsername(String username){
		UserDb dbUser = userMapper.retrieveUserByUsername(username);
		if(null == dbUser) {
			return new UserPojo();
		}
		UserPojo pojo = new UserPojo();
		pojo.setUsername(dbUser.getUsername());
		return pojo;
	}
	
	/**
	 * 判断用户名和密码是否正确
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(UserDb user) {
		return userMapper.authenticate(user) == 1;
	}
}
