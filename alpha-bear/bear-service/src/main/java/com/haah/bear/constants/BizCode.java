/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.constants;


/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午5:01:45
 */
public class BizCode {
	public static final BizCode SUCCESS = new BizCode(000000, "处理成功!");

	public static final BizCode USER_PASSWORD_NULL = new BizCode(100001, "用户名或密码为空！");
	public static final BizCode USER_NOT_EXIST = new BizCode(100001, "该用户不存在,请联系管理员。");
	public static final BizCode USER_WRONG_PASSWORD = new BizCode(100001, "密码错误！");

	private BizCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;

	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public BizCode msg(String message) {
		return new BizCode(this.code, message);
	}

	public boolean isSucceed() {
		return this.code == SUCCESS.code;
	}

	@Override
	public String toString() {
		return  message;
	}
}
