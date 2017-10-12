package com.shuyao.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.api.entity.TokenEntity;
import com.shuyao.modules.sys.dao.BaseDao;

/**
 * 用户Token
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-05 21:23:12
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
