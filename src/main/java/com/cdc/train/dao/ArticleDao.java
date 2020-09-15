package com.cdc.train.dao;

import com.cdc.train.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-25 15:25:17
 */
@Mapper
public interface ArticleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    Article queryById(int articleId);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Article> queryAllByLimit(Map<String,Object>params);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param article 实例对象
     * @return 对象列表
     */
    List<Article> queryAll(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleId 主键
     * @return 影响行数
     */
    int deleteById(Integer articleId);

    List<Article> selFavoriteByUserId(Map<String, Object> params);
}