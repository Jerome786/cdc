package com.cdc.train.controller;

import com.cdc.train.entity.User;
import com.cdc.train.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-08-25 15:40:01
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过openId和用户名查询该用户是否存在
     *
     * @param openId openId
     * @param username 用户名
     * @return 单条数据
     */
    @GetMapping("checkUser")
    public String selectOne(@RequestParam("openId") String openId, @RequestParam("username") String username) {
//        return this.userService.queryByOpenIdAndUsername(openId, username);
        return "访问成功。。openId："+openId+", username："+username;
    }

}