package com.shuyao.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.job.entity.ScheduleJobLogEntity;
import com.shuyao.modules.sys.dao.BaseDao;

/**
 * 定时任务日志
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-15
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
