/**
 * *#                                                                      #
 * #                      Copyright (c) 2017 by                           #
 * #          Shanghai Stock Exchange (SSE), Shanghai, China              #
 * #                       All rights reserved.                           #
 * #                                                                      #
 * *
 */
package com.haah.bear.api.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class ExceptionFilter extends ZuulFilter{

    private static final Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("error.status_code");
    }

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            Object e = ctx.get("error.exception");
            if (e != null && e instanceof ZuulException){
                ZuulException zuulException = (ZuulException)e;
                logger.error(String.format("Zuul failure detected: %s",zuulException.getMessage()), zuulException);
                ctx.remove("error.status_code");
                ctx.setResponseBody("Overriding Zuul Exception Body");
                ctx.getResponse().setContentType("application/json");
                ctx.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }catch (Exception e){
            logger.error("Exception filtering in customer error filter", e);
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
