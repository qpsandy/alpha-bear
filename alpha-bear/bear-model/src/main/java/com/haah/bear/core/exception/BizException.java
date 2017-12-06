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

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 6001985523527877411L;

	private BizCode bizCode;
	
	public BizException(BizCode bizCode) {
		super(bizCode.getMessage());
		this.bizCode = bizCode;
	}
	
	public BizException(BizCode bizCode, Exception e) {
		super(e);
		this.bizCode = bizCode;
	}

	public BizCode getBizCode() {
		return bizCode;
	}
	
	
}
