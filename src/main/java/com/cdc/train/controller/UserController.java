package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.entity.User;
import com.cdc.train.entity.dto.UserDTO;
import com.cdc.train.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
    public Result checkUser(@RequestBody Map<String,Object> params){
        if (params.isEmpty()||!params.containsKey("userId")) {
            return new Result(ResultCode.ERROR,"参数为空");
        }
        try {
            return userService.checkUser(params);
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    @RequestMapping("updateStatus")
    public Result updateStatus(@RequestBody Map<String,Object> params){
        if (params.isEmpty()||!params.containsKey("status")||!params.containsKey("userId")) {
            return new Result(ResultCode.ERROR,"参数为空");
        }
        try {
            return userService.updateStatus(params);
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    /***
     * 添加用户数据
     * @param user
     * @return
     */
    @PostMapping("addUser")
    public Result insertUser(@RequestBody UserDTO user){
        try {
            return userService.insert(user);
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }


    /***
     *  通过ID查询单条数据
     * @param userId
     * @return
     */
    @PostMapping("queryById")
    public Result queryById(@RequestBody Map<String,Object> params){
        if (params.isEmpty()&&params.containsKey("userId")) {
            return new Result(ResultCode.ERROR,"参数为空");
        }
        try {
            return userService.queryById(params.get("userId").toString());
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
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
     * @return
     */
    @PostMapping("queryAllUser")
    public Result queryAllUser(@RequestBody Map<String,Object> params) {
        try {
            return userService.queryAllUser(params);
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
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