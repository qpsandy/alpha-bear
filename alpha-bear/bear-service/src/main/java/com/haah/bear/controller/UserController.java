package com.haah.bear.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haah.bear.pojo.LoginPojo;

@RestController
@RequestMapping("user/")
public class UserController {

	@RequestMapping(path = "/login", method = RequestMethod.POST)
   	String login(@RequestBody LoginPojo lp) {
		return "Success";
   	}
}
