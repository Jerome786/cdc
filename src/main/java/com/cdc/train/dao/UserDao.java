package com.cdc.train.dao;

import com.cdc.train.common.Result;
import com.cdc.train.entity.User;
import com.cdc.train.entity.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-25 15:39:54
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserDTO queryById(String userId);

    /**
     * 通过openID查询单条数据
     *
     * @param user 用户对象
     * @return 实例对象
     */
    User queryByUser(@Param("user") User user);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<UserDTO> queryAll(Map<String,Object> params);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(@Param("user") User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

    int updateStatus(Map<String, Object> params);
}