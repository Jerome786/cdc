package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.entity.dto.UserDTO;
import com.cdc.train.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

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

    @RequestMapping("checkUser")
    public Result checkUser(@RequestBody Map<String, Object> params) {
        if (params.isEmpty() || !params.containsKey("userId")) {
            return new Result(ResultCode.ERROR, "参数为空");
        }
        try {
            return userService.checkUser(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    @RequestMapping("updateStatus")
    public Result updateStatus(@RequestBody Map<String, Object> params) {
        if (params.isEmpty() || !params.containsKey("status") || !params.containsKey("userId")) {
            return new Result(ResultCode.ERROR, "参数为空");
        }
        try {
            return userService.updateStatus(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    /***
     * 添加用户数据
     * @param user
     * @return
     */
    @PostMapping("addUser")
    public Result insertUser(@RequestBody UserDTO user) {
        try {
            return userService.insert(user);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }


    /***
     *  通过ID查询单条数据
     * @param params
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestBody Map<String, Object> params) {
        if (params.isEmpty() && params.containsKey("userId")) {
            return new Result(ResultCode.ERROR, "参数为空");
        }
        try {
            return userService.queryById(params.get("userId").toString());
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }


    /***
     * @return
     */
    @PostMapping("queryAllUser")
    public Result queryAllUser(@RequestBody Map<String, Object> params) {
        try {
            return userService.queryAllUser(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }


    /**
     * 根据用户所传id修改用户信息
     *
     * @param params
     * @return
     */
    @RequestMapping("updateUser")
    public Result update(@RequestBody Map<String, Object> params) {
        try {
            return userService.update(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }
}