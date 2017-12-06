/**
 * *#                                                                      #
 * #                      Copyright (c) 2017 by                           #
 * #          Shanghai Stock Exchange (SSE), Shanghai, China              #
 * #                       All rights reserved.                           #
 * #                                                                      #
 * *
 */
package com.haah.bear.api.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("applicationConfigure")
public class ApplicationConfigure implements InitializingBean {

    @Value("${pre.auth.header}")
    private String authHeader;

    public static String preAuthHeader;

    @Override
    public void afterPropertiesSet() throws Exception {
        preAuthHeader = authHeader;
    }
}
