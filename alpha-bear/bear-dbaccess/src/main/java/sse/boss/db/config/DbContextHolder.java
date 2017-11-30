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

/**
 * Database Context Holder for read-write separation
 * 
 * @author chsun
 * @date 2017年1月5日
 * @time 下午1:44:42
 */
public class DbContextHolder {

	private static Logger logger = LoggerFactory.getLogger(DbContextHolder.class);

	public enum DbType {
		READ, WRITE
	}

	private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<DbType>();

	public static void setDbType(DbType dbType) {
		if (dbType == null) {
			throw new NullPointerException();
		}
		logger.debug("Setting database context to " + dbType + ".");
		contextHolder.set(dbType);
	}

	public static DbType getDbType() {
		contextHolder.set(contextHolder.get() == null ? DbType.WRITE : contextHolder.get());
		return contextHolder.get();
	}

	public static void clearDbType() {
		logger.debug("Clearing database context.");
		contextHolder.remove();
	}
}
