package com.haah.bear.mapper;

import java.util.List;

import com.bear.db.feature.orm.mybatis.Page;
import com.haah.bear.core.model.db.HotelDb;

public interface HotelMapper {
	
	List<HotelDb> queryHotelByPage(Page<HotelDb> page, HotelDb hd);

	
}
