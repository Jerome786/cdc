package com.cdc.train.service;

import com.cdc.train.common.Result;
import com.cdc.train.entity.User;
import com.cdc.train.entity.dto.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-08-25 15:39:54
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    Result queryById(String userId);

    /**
     * 通过userId、nickname验证单条数据
     *
     * @param user 用户对象
     * @return 实例对象
     */
    User queryByUser(User user);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result insert(UserDTO user);

    /**
     * 修改数据
     *
     * @param params 实例对象
     * @return 实例对象
     */
    Result update(Map<String,Object> params);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);

    Result queryAllUser(Map<String,Object> params);

    Result updateStatus(Map<String, Object> params);

    Result checkUser(Map<String, Object> params);
}