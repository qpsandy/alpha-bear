package com.haah.bear.api.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.core.pojo.Result;

@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().println(JSONObject.toJSONString(Result.error(exception.getMessage(),HttpStatus.FORBIDDEN.value())));
        response.getWriter().close();
    }
}
