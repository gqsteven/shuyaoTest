package com.shuyao.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-17 10:07:30
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
