package com.haah.bear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haah.bear.core.pojo.Result;
import com.haah.bear.core.pojo.UserPojo;
import com.haah.bear.service.UserService;

@RestController
@RequestMapping("user/")
public class UserController extends BaseController {
//	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(path = "/login", method = RequestMethod.POST)
   	public Result login(@RequestBody UserPojo lp) {
		return Result.success();
   	}
	
	@RequestMapping(path = "/authentic", method = RequestMethod.POST)
   	public Result authentic(@RequestBody UserPojo user) {
		return userService.loginAuthenticate(user);
   	}

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public UserPojo retrieveUserByUsername(@RequestParam("username")String username) {
    	return userService.retrieveUserByUsername(username);
    }
	
	
}
