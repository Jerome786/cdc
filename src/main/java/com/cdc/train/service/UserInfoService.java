package com.cdc.train.service;

import com.cdc.train.entity.UserInfo;

import java.util.List;

/**
 * (UserInfo)表服务接口
 *
 * @author makejava
 * @since 2020-08-25 15:42:15
 */
public interface UserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserInfo queryById(String userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
    UserInfo insert(UserInfo userInfo);




}