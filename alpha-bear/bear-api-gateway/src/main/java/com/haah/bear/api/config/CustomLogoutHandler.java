package com.haah.bear.api.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.core.constants.BizCode;

@Component
public class CustomLogoutHandler implements LogoutSuccessHandler {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(BizCode.USER_LOGOUT_SUCCESS));
    }
}
