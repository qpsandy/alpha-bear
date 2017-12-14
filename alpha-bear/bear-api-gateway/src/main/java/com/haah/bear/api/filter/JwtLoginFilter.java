/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.api.utils.LoginFilterUtils;
import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtLoginFilter.class);

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login"));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String body = IOUtils.toString(request.getInputStream(),"UTF-8");
        logger.info(String.format("request body: %s", body));
        String sslCert = obtainSslCert(request);
        logger.info(String.format("------------------ssl_cert: %s", sslCert));
        if (!HttpMethod.POST.name().equals(request.getMethod())){
            if (logger.isDebugEnabled()){
                logger.debug(String.format("Authentication method not supported. Request Method: %s", request.getMethod()));
            }
        }
        UserPojo user = JSONObject.parseObject(body, UserPojo.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                user,
                null,
                Collections.<GrantedAuthority>emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication auth) throws IOException, ServletException {
        LoginFilterUtils.successfulAuthentication(response, auth);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JSONObject.toJSONString(Result.error(failed.getMessage())));
    }

    protected String obtainSslCert(HttpServletRequest request) {
        return null;
    }
}
