package com.shuyao.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.shuyao.common.exception.SYException;
import com.shuyao.modules.sys.dao.SysConfigDao;
import com.shuyao.modules.sys.entity.SysConfigEntity;
import com.shuyao.modules.sys.service.SysConfigService;

/**
 * 系统配置信息
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;
	
	@Override
	@Transactional
	public void save(SysConfigEntity config) {
		sysConfigDao.save(config);
	}

	@Override
	public void update(SysConfigEntity config) {
		sysConfigDao.update(config);
	}

	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}

	@Override
	public List<SysConfigEntity> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	@Override
	public SysConfigEntity queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigDao.queryByKey(key);
		if(StringUtils.isBlank(value)){
			return defaultValue;
		}
		return value;
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return new Gson().fromJson(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new SYException("获取参数失败");
		}
	}
}