package com.shuyao.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.sys.entity.SysRoleDeptEntity;

/**
 * 角色与部门对应关系
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Mapper
public interface SysRoleDeptDao extends BaseDao<SysRoleDeptEntity> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
}
