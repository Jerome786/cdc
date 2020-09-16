package com.cdc.train.service;

import com.cdc.train.common.Result;

import java.util.Map;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2020-08-25 15:25:23
 */
public interface ArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    Result queryById(int articleId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    Result queryAllByLimit(Map<String,Object> params);




    Result selFavoriteByUserId(Map<String, Object> params);

    Result addFavorite(Map<String, Object> params);

    Result delFavorite(Map<String, Object> params);

    Result selFavorite(Map<String, Object> params);
}