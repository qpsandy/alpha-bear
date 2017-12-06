/**
 * *#                                                                      #
 * #                      Copyright (c) 2017 by                           #
 * #          Shanghai Stock Exchange (SSE), Shanghai, China              #
 * #                       All rights reserved.                           #
 * #                                                                      #
 * *
 */
package com.haah.bear.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haah.bear.core.constants.BizCode;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ZuulExceptionController extends AbstractErrorController{

    private static final Logger logger = LoggerFactory.getLogger(ZuulExceptionController.class);

    @Value("${error.path:/error}")
    private String errorPath;

    @Value("${server.error.path:${error.path:/error}}")
    private String url;

    public ZuulExceptionController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(value = "$error.path:/error", produces = "application/json;charset=UTF-8")
    public ResponseEntity<BizCode> error(HttpServletRequest request){
        HttpStatus status = getStatus(request);
        final Throwable exc = (Throwable) request.getAttribute("java.servlet.error.exception");
        logger.error(exc.getMessage(), exc);
        return ResponseEntity.status(status).body(BizCode.SYSTEM_BUSY_ERROR);
    }
}
