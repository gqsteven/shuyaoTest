package com.shuyao.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
    
    SysUserTokenEntity queryByUserId(Long userId);

    SysUserTokenEntity queryByToken(String token);
	
}
