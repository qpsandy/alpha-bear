/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2016 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package sse.boss.db.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import sse.boss.db.annotation.ReadOnlyConnection;
import sse.boss.db.config.DbContextHolder;
import sse.boss.db.config.DbContextHolder.DbType;

@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {
	
	private static final Logger logger = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);
	
	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
		DbType currentDbType = DbContextHolder.getDbType();
		try {
			logger.debug("Setting database connection to read only.");
			DbContextHolder.setDbType(DbContextHolder.DbType.READ);
			return proceedingJoinPoint.proceed();
		}
		finally {
			DbContextHolder.setDbType(currentDbType);
			logger.debug("Restoring database connection to " + currentDbType + ".");
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
