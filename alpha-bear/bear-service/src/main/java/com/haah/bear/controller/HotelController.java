package com.haah.bear.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haah.bear.db.HotelDb;
import com.haah.bear.pojo.HotelPojo;

@RestController
@RequestMapping("hotel/")
public class HotelController {
	@RequestMapping(path = "/", method = RequestMethod.GET)
   	public List<HotelDb> list() {
		List<HotelDb> list = new ArrayList<HotelDb>();
		HotelDb hp = null;
		hp = new HotelDb("100001", "hotel1", "豪华型", "单人早餐", "无限", "涨10", "+10", new Date());
		list.add(hp);
		hp = new HotelDb("100002", "hotel2", "行政房", "单人早餐", "清空", "涨10", "+11", new Date());
		list.add(hp);
		hp = new HotelDb("100003", "hotel3", "豪华型", "无", "无限", "跌10", "-10", new Date());
		list.add(hp);
		
		return list;
   	}
}
