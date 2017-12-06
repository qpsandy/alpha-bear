/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haah.bear.api.client.fallback.UserServiceClientFallback;
import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;

import feign.Headers;

@FeignClient(name = "bear-service" + "${PRODUCER_TAG}", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

    @Headers("Connection: close")
	@RequestMapping(value = "/user/validate", method = RequestMethod.POST)
	Result authenticate(@RequestBody UserPojo user);
    
    @Headers("Connection: close")
    @RequestMapping(value = "/user/username", method = RequestMethod.GET)
    UserPojo retrieveUserByUsername(@RequestParam("username")String username);
	
}
