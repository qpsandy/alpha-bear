package com.haah.bear.service;

import com.bear.db.feature.orm.mybatis.Page;
import com.haah.bear.core.model.db.HotelDb;

public interface HotelService {
	
	public Page<HotelDb> retrieveHotelByPage(Page<HotelDb> page, HotelDb hd);
}
