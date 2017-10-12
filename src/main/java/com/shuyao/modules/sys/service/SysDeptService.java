package com.shuyao.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.shuyao.modules.sys.entity.SysDeptEntity;

/**
 * 部门管理
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
public interface SysDeptService {
	
	SysDeptEntity queryObject(Long deptId);
	
	List<SysDeptEntity> queryList(Map<String, Object> map);

	void save(SysDeptEntity sysDept);
	
	void update(SysDeptEntity sysDept);
	
	void delete(Long deptId);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	/**
	 * 获取子部门ID(包含本部门ID)，用于数据过滤
	 */
	String getSubDeptIdList(Long deptId,boolean self);
	/**
	 * 获取部门ID 根据菜单
	 */
	List<SysDeptEntity> getSubDeptIdByMenu(Map<String, Object> map);

}
