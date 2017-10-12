package com.shuyao.modules.api.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shuyao.common.utils.Resp;
import com.shuyao.common.validator.Assert;
import com.shuyao.modules.api.annotation.AuthIgnore;
import com.shuyao.modules.api.service.TokenService;
import com.shuyao.modules.api.service.UserService;

/**
 * API登录授权
 *
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-05 21:23:12
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     */
    @AuthIgnore
    @PostMapping("login")
    public Resp login(String mobile, String password){
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return Resp.ok(map);
    }

}
