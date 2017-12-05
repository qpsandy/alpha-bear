/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.bear.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * read write separate annotation service methods with this annotation can be
 * restricted to read only database connection
 * 
 * @author chsun
 * @date 2017年1月18日
 * @time 下午2:01:50
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnlyConnection {

}
