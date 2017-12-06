/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.core.model.db;

import java.io.Serializable;

/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午4:50:17
 */
public class UserDb extends BaseDb implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public UserDb(){}
	
	/**
	 * @param username
	 * @param password
	 */
	public UserDb(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
