package com.cdc.train.service;

import com.cdc.train.entity.UserRole;

import java.util.List;

/**
 * (UserRole)表服务接口
 *
 * @author makejava
 * @since 2020-08-25 15:42:44
 */
public interface UserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserRole queryById(String userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole userRole);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole userRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);

}