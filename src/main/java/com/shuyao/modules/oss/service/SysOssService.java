package com.shuyao.modules.oss.service;

import java.util.List;
import java.util.Map;

import com.shuyao.modules.oss.entity.SysOssEntity;

/**
 * 文件上传
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-07
 */
public interface SysOssService {
	
	SysOssEntity queryObject(Long id);
	
	List<SysOssEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysOssEntity sysOss);
	
	void update(SysOssEntity sysOss);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
