package com.cdc.train.controller;

import com.cdc.train.entity.UserRole;
import com.cdc.train.service.UserRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserRole)表控制层
 *
 * @author makejava
 * @since 2020-08-25 15:42:44
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserRole selectOne(String id) {
        return this.userRoleService.queryById(id);
    }

}