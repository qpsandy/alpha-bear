/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2016 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package sse.boss.db.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author chsun
 * @date 2017年1月6日
 * @time 上午10:46:34
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

	private static Logger logger = LoggerFactory.getLogger(MyAbstractRoutingDataSource.class);

	public MyAbstractRoutingDataSource() {
	}

	@Override
	protected Object determineCurrentLookupKey() {

		logger.info("Current database context is " + DbContextHolder.getDbType() + ".");

		return DbContextHolder.getDbType();
	}

}
