package com.shuyao.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

/**
 * 返回数据
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-17 19:12:45
 */
public class Resp extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public Resp() {
		put("code", 0);
	}
	
	public static Resp erroresp() {
		return erroresp(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static Resp erroresp(String msg) {
		return erroresp(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static Resp erroresp(int code, String msg) {
		Resp resp = new Resp();
		resp.put("code", code);
		resp.put("msg", msg);
		return resp;
	}

	public static Resp ok(String msg) {
		Resp resp = new Resp();
		resp.put("msg", msg);
		return resp;
	}
	
	public static Resp ok(Map<String, Object> map) {
		Resp resp = new Resp();
		resp.putAll(map);
		return resp;
	}
	
	public static Resp ok() {
		return new Resp();
	}

	public Resp put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
