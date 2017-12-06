package com.haah.bear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bear.db.feature.orm.mybatis.Page;
import com.haah.bear.core.model.db.HotelDb;
import com.haah.bear.mapper.HotelMapper;

@Service("hotelService")
public class HotelService {
	
	@Autowired
	private HotelMapper hotelMapper;
	
	public List<HotelDb> retrieveHotelByPage(Page<HotelDb> page, HotelDb hd) {
		return hotelMapper.retrieveHotelByPage(page, hd);
	}
}
