package com.shuyao.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shuyao.modules.api.entity.UserEntity;
import com.shuyao.modules.sys.dao.BaseDao;

/**
 * 用户
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-05 21:23:12
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
