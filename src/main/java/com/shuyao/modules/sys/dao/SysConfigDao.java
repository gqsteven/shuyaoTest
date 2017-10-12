package com.shuyao.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shuyao.modules.sys.entity.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {
	
	/**
	 * 根据key，查询value
	 */
	String queryByKey(String paramKey);
	
	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
