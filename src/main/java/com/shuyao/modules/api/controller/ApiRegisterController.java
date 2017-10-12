package com.shuyao.modules.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shuyao.common.utils.Resp;
import com.shuyao.common.validator.Assert;
import com.shuyao.modules.api.annotation.AuthIgnore;
import com.shuyao.modules.api.service.UserService;

/**
 * 注册
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-05 21:23:12
 */
@RestController
@RequestMapping("/api")
public class ApiRegisterController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @AuthIgnore
    @PostMapping("register")
    public Resp registeresp(String mobile, String password){
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.save(mobile, password);

        return Resp.ok();
    }
}
