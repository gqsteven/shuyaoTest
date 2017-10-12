package com.shuyao.modules.statistics.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.statistics.entity.AnalyzeTanzhenEntity;
import com.shuyao.modules.sys.dao.BaseDao;

/**
 * 统计分析
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-07 00:29:39
 */
@Mapper
public interface AnalyzeTanzhenDao extends BaseDao<AnalyzeTanzhenEntity> {
	List<Map<String, Object>> queryByCon(Map<String, Object> map);
}
