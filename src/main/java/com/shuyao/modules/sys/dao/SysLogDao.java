package com.shuyao.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
