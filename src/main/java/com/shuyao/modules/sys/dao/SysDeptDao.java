package com.shuyao.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.sys.entity.SysDeptEntity;

/**
 * 部门管理
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
    
    /**
     * 查询menu的数据部门
     * @param menu
     * @param roleFilter
     * @return
     */
    List<SysDeptEntity> queryDetpIdByMenuList(Map<String, Object> map);
}
