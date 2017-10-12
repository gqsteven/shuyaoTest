package com.shuyao.modules.job.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.job.entity.ScheduleJobEntity;
import com.shuyao.modules.sys.dao.BaseDao;

/**
 * 定时任务
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-15
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
