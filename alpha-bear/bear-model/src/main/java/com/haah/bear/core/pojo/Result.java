/*########################################################################
 *#                                                                      #
 *#                      Copyright (c) 2016 by                           #
 *#          Shanghai Stock Exchange (SSE), Shanghai, China              #
 *#                       All rights reserved.                           #
 *#                                                                      #
 *########################################################################
*/

package com.haah.bear.core.pojo;

import java.io.Serializable;

import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7221136143337499523L;

	private boolean succeed = false;

	private String message;

	private Integer status;

	private transient Exception capturedException;

	private Object resultObject;

	public Result() {

	}

	private Result(boolean succ) {
		this(succ, "");
	}

	private Result(boolean succ, String message) {
		this.succeed = succ;
		this.message = message;
	}

	public Result(boolean succeed, String message, Integer status) {
		this.succeed = succeed;
		this.message = message;
		this.status = status;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public Result setSucceed(boolean succeed) {
		this.succeed = succeed;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Exception getCapturedException() {
		return capturedException;
	}

	public void setCapturedException(Exception capturedException) {
		this.capturedException = capturedException;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public Result setResultObject(Object resultObject) {
		this.resultObject = resultObject;
		return this;
	}

	public static Result success() {
		return new Result(true);
	}

	public static Result success(Object obj) {
		Result result = Result.success();
		result.setResultObject(obj);
		return result;
	}

	public static String successToJsonString(Object obj) {
		Result result = Result.success();
		result.setResultObject(obj);
		return JSONObject.toJSONString(result, SerializerFeature.NotWriteRootClassName);
	}
	
	public static Result success(String message) {
		Result result = Result.success();
		result.setMessage(message);
		return result;
	}

	public static Result error() {
		return new Result(false);
	}

	public static Result error(String message) {
		return new Result(false, message);
	}

	public static Result error(String message,Integer status) {
		return new Result(false, message, status);
	}

	public static Result fromOpDB(int count) {
		return count > 0 ? new Result(true) : new Result(false);
	}

	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		json.put("success", succeed ? true : false);
		// for easyui-edatagrid
		json.put("isError", succeed ? false : true);
		json.put("message", message == null ? "" : message.trim());
		return json;
	}

	public String toJSONString() {
		return toJsonObject().toJSONString();
	}

	/**
	 * 输出beanValidation校验信息
	 * 
	 * @param bindingResult
	 * @return
	 */
	public static Result getValidationMessage(BindingResult bindingResult) {
		return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
