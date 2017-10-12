package com.shuyao.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shuyao.common.utils.Resp;

/**
 * 异常处理器
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class SYExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(SYException.class)
	public Resp handleSYException(SYException e){
		Resp resp = new Resp();
		resp.put("code", e.getCode());
		resp.put("msg", e.getMessage());

		return resp;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Resp handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return Resp.erroresp("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public Resp handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return Resp.erroresp("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public Resp handleException(Exception e){
		logger.error(e.getMessage(), e);
		return Resp.erroresp();
	}
}
