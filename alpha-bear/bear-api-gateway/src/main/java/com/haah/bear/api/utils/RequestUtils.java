/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午7:18:31
 */
public class RequestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 获取客户端用户登录IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        logger.info(String.format("x-forwarded-for IP: %s", ip));

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("x-real-ip");
            logger.info(String.format("x-real-ip : %s", ip));
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("x-proxy-ip");
            logger.info(String.format("x-proxy-ip : %s", ip));
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
            logger.info(String.format("Proxy-Client-IP : %s", ip));
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info(String.format("WL-Proxy-Client-IP : %s", ip));
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
            logger.info(String.format("request.getRemoteAddr() : %s", ip));
        }

        return ip;
    }
}
