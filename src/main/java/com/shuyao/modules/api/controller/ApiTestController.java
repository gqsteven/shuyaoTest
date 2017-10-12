package com.shuyao.modules.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shuyao.common.utils.Resp;
import com.shuyao.modules.api.annotation.AuthIgnore;
import com.shuyao.modules.api.annotation.LoginUser;
import com.shuyao.modules.api.entity.TokenEntity;
import com.shuyao.modules.api.entity.UserEntity;

/**
 * API测试接口
 *
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-05 21:23:12
 */
@RestController
@RequestMapping("/api")
public class ApiTestController {

    /**
     * 获取用户信息
     */
    @GetMapping("userInfo")
    public Resp userespInfo(@LoginUser UserEntity user){
        return Resp.ok().put("user", user);
    }

    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("notToken")
    public Resp notToken(){
        return Resp.ok().put("msg", "无需token也能访问。。。");
    }

    /**
     * 接收JSON数据
     */
    @PostMapping("jsonData")
    public Resp jsonData(@LoginUser UserEntity user, @RequestBody TokenEntity token){
        return Resp.ok().put("user", user).put("token", token);
    }
}
