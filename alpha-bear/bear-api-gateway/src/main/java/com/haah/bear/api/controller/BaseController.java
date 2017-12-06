/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.api.client.UserServiceClient;
import com.haah.bear.core.pojo.UserPojo;

/**
 * 提供controller 的公用方法
 * @Auther xin.baojian
 * @date 2017/6/5
 * @time 15:12
 *
 */
public class BaseController {

    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 从SecurityContext 获取权限信息
     * @return
     */
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前请求用户
     * @return
     */
    public UserPojo getUser(){
        return userServiceClient.retrieveUserByUsername(getUserContext().getUsername());
    }

    /**
     * 根据Authentication 获取用户信息
     * @return
     */
    public UserPojo getUserContext(){
        return JSONObject.parseObject(getAuthentication().getPrincipal().toString(),UserPojo.class);
    }

}
