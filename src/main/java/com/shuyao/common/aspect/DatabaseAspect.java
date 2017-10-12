package com.shuyao.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.shuyao.common.annotation.DataBase;
import com.shuyao.dynamicdatasource.DataSourceContext;
import com.shuyao.dynamicdatasource.DynamicDataSource;


/**
 * 多数剧源，切面处理类
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-17
 */
@Aspect
@Component
public class DatabaseAspect {

	@AfterReturning("@annotation(com.shuyao.common.annotation.DataBase)")
	public void afterReturning() throws Throwable {  
		
		DynamicDataSource.clearDataSource();
	}
	
	@Before("@annotation(com.shuyao.common.annotation.DataBase)")
	public void before(JoinPoint point) throws Throwable {  
		    MethodSignature signature = (MethodSignature) point.getSignature();
		    Method method = signature.getMethod();
	        if (method.isAnnotationPresent(DataBase.class)) {  
		        	DataBase datasource = method.getAnnotation(DataBase.class);  
		        	System.out.println("ahah" + datasource.value());
		        	DynamicDataSource.setDataSource(datasource.value());  
	        } else {  
	        		DynamicDataSource.setDataSource(DataSourceContext.FIRST.getName());  
	        }  
	  
	    }  
}
