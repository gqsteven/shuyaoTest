package com.shuyao.modules.statistics.service;

import java.util.List;
import java.util.Map;

import com.shuyao.modules.statistics.entity.AnalyzeTanzhenEntity;

/**
 * 统计分析
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-07 00:29:39
 */
public interface AnalyzeTanzhenService {
	
	AnalyzeTanzhenEntity queryObject(Long id);
	
	List<AnalyzeTanzhenEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AnalyzeTanzhenEntity analyzeTanzhen);
	
	void update(AnalyzeTanzhenEntity analyzeTanzhen);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	public List<Map<String, Object>> queryByCon(Map<String, Object> map);
}
