package com.shuyao.common.utils;

/**
 * Redis所有Keys
 *
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
