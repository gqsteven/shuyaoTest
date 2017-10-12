package com.shuyao.modules.sys.service;

import java.util.List;


/**
 * 角色与部门对应关系
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
public interface SysRoleDeptService {
	
	void saveOrUpdate(Long roleId, List<Long> deptIdList);
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
	
}
