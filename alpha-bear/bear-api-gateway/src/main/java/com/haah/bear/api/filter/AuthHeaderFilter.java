/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.haah.bear.api.utils.RequestUtils;
import com.haah.bear.core.constants.BizCode;
import com.haah.bear.core.exception.BizException;
import com.haah.bear.core.pojo.UserPojo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthHeaderFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthHeaderFilter.class);

    @Autowired
    HttpServletRequest request;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (!"anonymousUser" .equals(authentication.getPrincipal().toString())) {
            UserPojo userContextPojo = JSONObject.parseObject(authentication.getPrincipal().toString(), UserPojo.class);

            try {
                requestContext.getRequest().setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.debug("Setting the character encoding format failed!", e);
                throw new BizException(BizCode.CONVERT_ENCODING_ERROR, e);
            }
            requestContext.addZuulRequestHeader("username", userContextPojo.getUsername());
        }
        requestContext.addZuulRequestHeader("remoteIp", RequestUtils.getIpAddr(request));
        return null;
    }


}
