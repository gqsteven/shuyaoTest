package com.shuyao.modules.statistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuyao.modules.statistics.dao.AnalyzeTanzhenDao;
import com.shuyao.modules.statistics.entity.AnalyzeTanzhenEntity;
import com.shuyao.modules.statistics.service.AnalyzeTanzhenService;



@Service("analyzeTanzhenService")
public class AnalyzeTanzhenServiceImpl implements AnalyzeTanzhenService {
	@Autowired
	private AnalyzeTanzhenDao analyzeTanzhenDao;
	
	@Override
	public AnalyzeTanzhenEntity queryObject(Long id){
		return analyzeTanzhenDao.queryObject(id);
	}
	
	@Override
	public List<AnalyzeTanzhenEntity> queryList(Map<String, Object> map){
		return analyzeTanzhenDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return analyzeTanzhenDao.queryTotal(map);
	}
	
	@Override
	public void save(AnalyzeTanzhenEntity analyzeTanzhen){
		analyzeTanzhenDao.save(analyzeTanzhen);
	}
	
	@Override
	public void update(AnalyzeTanzhenEntity analyzeTanzhen){
		analyzeTanzhenDao.update(analyzeTanzhen);
	}
	
	@Override
	public void delete(Long id){
		analyzeTanzhenDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		analyzeTanzhenDao.deleteBatch(ids);
	}
	
	@Override
	public List<Map<String, Object>> queryByCon(Map<String, Object> map){
		return analyzeTanzhenDao.queryByCon(map);
	}
}
