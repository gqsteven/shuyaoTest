package com.shuyao.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.sys.entity.SysRoleMenuEntity;

/**
 * 角色与菜单对应关系
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
