/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2017 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/
package com.haah.bear.core.constants;

/**
 * @author qpzhou
 * @date 2017年12月6日
 * @time 下午5:01:45
 */
public class BizCode {
	public static final BizCode SUCCESS = new BizCode(000000, "处理成功!");

	public static final BizCode USER_PASSWORD_NULL = new BizCode(100001, "用户名或密码为空！");
	public static final BizCode USER_NOT_EXIST = new BizCode(100002, "该用户不存在,请联系管理员。");
	public static final BizCode USER_WRONG_PASSWORD = new BizCode(100003, "密码错误！");
	public static final BizCode USER_LOGIN_SUCCESS = new BizCode(SUCCESS.code, "登录成功！");
	public static final BizCode USER_LOGOUT_SUCCESS = new BizCode(100005, "登出成功！");
	public static final BizCode INVALID_USER_CREDENTIALS = new BizCode(100006, "用户名或密码无效！");
	public static final BizCode USER_ALREADY_LOGOUT = new BizCode(100007, "用户已登出，请重新登录!");
	
	public static final BizCode SYSTEM_BUSY_ERROR = new BizCode(600001, "系统繁忙");
	public static final BizCode CONVERT_ENCODING_ERROR = new BizCode(600002, "字符编码转换出错");


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
