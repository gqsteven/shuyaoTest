package com.shuyao.modules.sys.service;

import com.shuyao.common.utils.Resp;
import com.shuyao.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-02
 */
public interface SysUserTokenService {

	SysUserTokenEntity queryByUserId(Long userId);

	SysUserTokenEntity queryByToken(String token);
	
	void save(SysUserTokenEntity token);
	
	void update(SysUserTokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Resp createToken(long userId);

}
