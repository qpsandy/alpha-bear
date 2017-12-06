/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haah.bear.core.pojo.UserPojo;

@Controller
public class LoginController extends BaseController{

    /**
     * 登录接口
     * @param user
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody UserPojo user) {
        return "";
    }
    
    /**
     * 登出接口
     * @param request request
     * @return
     */
    @RequestMapping("/logouts")
    @PreAuthorize("authenticated")
    public String logout(HttpServletRequest request) {
        return "forward:/logout";
    }
}
