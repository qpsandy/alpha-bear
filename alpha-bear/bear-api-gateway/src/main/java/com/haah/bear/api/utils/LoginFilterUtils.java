/**
 * *#                                                                      #
 * #                      Copyright (c) 2017 by                           #
 * #          Shanghai Stock Exchange (SSE), Shanghai, China              #
 * #                       All rights reserved.                           #
 * #                                                                      #
 * *
 */
package com.haah.bear.api.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.core.constants.BizCode;

/**
 * @Auther xin.baojian
 * @date 2017/11/29
 * @time 15:28
 */
public class LoginFilterUtils {

    public static void successfulAuthentication(HttpServletResponse response, Authentication auth) throws IOException {
//        UserPojo context = JSONObject.parseObject(auth.getPrincipal().toString(), UserPojo.class);
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader(JwtTokenUtils.HEADER_STRING,
                String.format("%s %s", JwtTokenUtils.TOKEN_PREFIX,
                        JwtTokenUtils.generateToken(auth.getName(), auth.getAuthorities())));
        response.getWriter().write(JSONObject.toJSONString(BizCode.USER_LOGIN_SUCCESS));

    }
}
