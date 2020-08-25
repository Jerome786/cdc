package com.cdc.train.service;

import com.cdc.train.entity.UserArticle;

import java.util.List;

/**
 * (UserArticle)表服务接口
 *
 * @author makejava
 * @since 2020-08-25 15:41:51
 */
public interface UserArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserArticle queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserArticle> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userArticle 实例对象
     * @return 实例对象
     */
    UserArticle insert(UserArticle userArticle);

    /**
     * 修改数据
     *
     * @param userArticle 实例对象
     * @return 实例对象
     */
    UserArticle update(UserArticle userArticle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}