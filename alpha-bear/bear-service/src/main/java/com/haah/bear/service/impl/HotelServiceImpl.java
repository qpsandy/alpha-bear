/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bear.db.feature.orm.mybatis.Page;
import com.haah.bear.core.model.db.HotelDb;
import com.haah.bear.mapper.HotelMapper;
import com.haah.bear.service.HotelService;


/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午4:46:02
 */
@Service("hotelService")
public class HotelServiceImpl implements HotelService {
//	private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
	
	@Autowired
	private HotelMapper hotelMapper;
	
	@Override
	public Page<HotelDb> retrieveHotelByPage(Page<HotelDb> page, HotelDb hd) {
		hotelMapper.queryHotelByPage(page, hd);
		return page;
	}
}
