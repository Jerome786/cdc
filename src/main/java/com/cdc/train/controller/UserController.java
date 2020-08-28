package com.cdc.train.controller;

import com.cdc.train.entity.User;
import com.cdc.train.service.UserService;
import org.springframework.web.bind.annotation.*;

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
     * @param user 用户对象
     * @return 单条数据
     */
    @PostMapping("checkUser")
    public User checkUser(@RequestBody User user) {

        return this.userService.queryByUser(user);
    }

}