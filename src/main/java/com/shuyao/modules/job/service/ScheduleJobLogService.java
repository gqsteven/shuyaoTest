package com.shuyao.modules.job.service;

import java.util.List;
import java.util.Map;

import com.shuyao.modules.job.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-15
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLogEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLogEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogEntity log);
	
}
