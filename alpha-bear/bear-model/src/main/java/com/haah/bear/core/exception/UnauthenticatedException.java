/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2016 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.core.exception;

import com.haah.bear.core.constants.BizCode;

public class UnauthenticatedException extends RuntimeException {

	/**
	 * @author chuang
	 */
	private static final long serialVersionUID = -4090960100726662748L;

	private BizCode bizCode;
	
	public UnauthenticatedException(BizCode bizCode) {
		this.bizCode = bizCode;
	}

	public BizCode getBizCode() {
		return bizCode;
	}
	
}
