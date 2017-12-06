/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	@Autowired
	protected HttpServletRequest request;

	public String getUserId() {
		return request.getHeader("userId");
	}

	public String getUserName() {
		return request.getHeader("username");
	}

	public String getRemoteIp() {
		return request.getHeader("remoteIp");
	}

}
