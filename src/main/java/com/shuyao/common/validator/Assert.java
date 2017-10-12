package com.shuyao.common.validator;

import org.apache.commons.lang.StringUtils;

import com.shuyao.common.exception.SYException;

/**
 * 数据校验
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-17 15:18:09
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SYException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SYException(message);
        }
    }
}
