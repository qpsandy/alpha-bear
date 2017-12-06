/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;

import io.swagger.annotations.Api;

@Api(value = "/users" ,description = "User管理")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    /**
     * 根据用户名获取用户信息
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result retrieveUser(){
        UserPojo pojo = new UserPojo();
        BeanUtils.copyProperties(getUser(), pojo);
        return Result.success().setResultObject(pojo);
    }

}
