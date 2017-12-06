/**
 * *#                                                                      #
 * #                      Copyright (c) 2017 by                           #
 * #          Shanghai Stock Exchange (SSE), Shanghai, China              #
 * #                       All rights reserved.                           #
 * #                                                                      #
 * *
 */
package com.haah.bear.api.provider;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.api.client.UserServiceClient;
import com.haah.bear.api.enums.LoginStatusEnum;
import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;

@Component
public class BasicAuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthenticationProvider.class);

    @Autowired
    private UserServiceClient userService;

    @SuppressWarnings("unchecked")
    protected Authentication authenticate(UserPojo user) {
        Result userResult = userService.authenticate(user);

        if (!userResult.isSucceed()) {
            throw new BadCredentialsException(userResult.getMessage());
        }

        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) userResult.getResultObject();

        UserPojo userPojo = new UserPojo();
        userPojo.setUsername(map.get("username").toString());

        //insert login logs..
        logger.info(String.format("user: %s Login Success！，", userPojo.getUsername()));
        //判断用户是否在登录状态
        if (map.get("userStatus") != null &&
                !LoginStatusEnum.LOGIN.getCode().toString().equals(map.get("userStatus").toString())) {
            user = new UserPojo();
            user.setUserStatus(LoginStatusEnum.LOGIN.getCode().toString());
//            Result loginResult = userService.changeUserStatus(user.getUsername(), LoginStatusEnum.LOGIN.getCode().toString());
//            if (loginResult.isSucceed()) {
//                logger.info(String.format("user: %s Modify the login status successful!", userPojo.getUsername()));
//            }
        } else {
            logger.info(String.format("user: %s Logined~", userPojo.getUsername()));
        }

        return new UsernamePasswordAuthenticationToken(JSONObject.toJSONString(userPojo), user.getPassword(),
                null);
    }
}
