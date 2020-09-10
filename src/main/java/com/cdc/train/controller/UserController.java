package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.entity.User;
import com.cdc.train.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * 通过 userId 和用户名查询该用户是否存在
     *
     * @param user 用户对象
     * @return 单条数据
     */
    @PostMapping("checkUser")
    public Result queryByUser(@RequestBody User user) {
        User checkUser = this.userService.queryByUser(user);
        if (checkUser != null){
            return new Result(ResultCode.SUCCESS,checkUser);
        }
            return new Result(ResultCode.PARAM_IS_INVALID);
    }

    /***
     * 添加用户数据
     * @param user
     * @return
     */
    @PostMapping("addUser")
    public Result insertUser(@RequestBody User user){
        int num = userService.insert(user);
        if(num > 0 ){
            return new Result(ResultCode.SUCCESS,"添加成功");
        }
        return new Result(ResultCode.PARAM_IS_INVALID,"添加失败");
    }


    /***
     *  通过ID查询单条数据
     * @param userId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestParam String userId){
        User user =  userService.queryById(userId);
        if(user != null ){
            return new Result(ResultCode.SUCCESS,user);
        }
        return new Result(ResultCode.PARAM_IS_INVALID,"该用户不存在");
    }


    /***
     *  通过userId 删除用户数据
     * @param userId
     * @return
     */
    @PostMapping("deleteUserById")
    public Result deleteById(String userId){
       boolean flag =  userService.deleteById(userId);
      if(flag){
          return new Result(ResultCode.SUCCESS,"删除成功");
      }
      return new Result(ResultCode.PARAM_IS_INVALID,"删除失败");
    }

    /***
     *  通过 offset 和 limit
     * @param offset
     * @param limit
     * @return
     */
    @PostMapping("queryAllByLimit")
    public Result queryAllByLimit(int offset, int limit) {
        List<User> listUser = null;
        listUser = userService.queryAllByLimit(offset, limit);
        return new Result(ResultCode.SUCCESS, listUser);
    }


    /**
     * 根据用户所传id修改用户信息
     * @param user
     * @return
     */
    @PutMapping("updateUser")
    public Result update(@RequestBody User user){
        User updateUser = userService.update(user);
        if(updateUser != null ){
            return new Result(ResultCode.SUCCESS,updateUser);
        }
        return new Result(ResultCode.PARAM_IS_INVALID,"修改失败");
    }
}