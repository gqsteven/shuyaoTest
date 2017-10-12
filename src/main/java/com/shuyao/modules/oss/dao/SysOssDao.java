package com.shuyao.modules.oss.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.oss.entity.SysOssEntity;
import com.shuyao.modules.sys.dao.BaseDao;

/**
 * 文件上传
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-07
 */
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
	
}
