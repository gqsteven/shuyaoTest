package com.shuyao.modules.api.service;


import java.util.Map;

import com.shuyao.modules.api.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-05
 */
public interface TokenService {

	TokenEntity queryByUserId(Long userId);

	TokenEntity queryByToken(String token);
	
	void save(TokenEntity token);
	
	void update(TokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(long userId);

}
