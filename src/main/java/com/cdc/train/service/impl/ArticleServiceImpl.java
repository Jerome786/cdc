package com.cdc.train.service.impl;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.dao.ArticleDao;
import com.cdc.train.dao.UserArticleDao;
import com.cdc.train.entity.Article;
import com.cdc.train.entity.UserArticle;
import com.cdc.train.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2020-08-25 15:25:23
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Resource
    private UserArticleDao userArticleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param articleId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(int articleId) {
        Article article = articleDao.queryById(articleId);
        if (article == null){
            return new Result(ResultCode.ERROR,"文章不存在");
        }
        return new Result(ResultCode.SUCCESS,article);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public Result queryAllByLimit(Map<String,Object> params) {
        List<Article> articles = this.articleDao.queryAllByLimit(params);
        return new Result(ResultCode.SUCCESS,articles);
    }

    @Override
    public Result selFavoriteByUserId(Map<String, Object> params) {

        return new Result(ResultCode.SUCCESS,articleDao.selFavoriteByUserId(params));
    }

    @Override
    public Result addFavorite(Map<String, Object> params) {
        if (params.isEmpty()||!params.containsKey("userId")||!params.containsKey("articleId")) {
            return new Result(ResultCode.ERROR,"参数错误");
        }
        params.put("favorite",1);
        articleDao.addFavorite(params);
        return new Result(ResultCode.SUCCESS,"收藏成功");
    }

    @Override
    public Result delFavorite(Map<String, Object> params) {
        if (params.isEmpty()||!params.containsKey("userId")||!params.containsKey("articleId")) {
            return new Result(ResultCode.ERROR,"参数错误");
        }
        articleDao.delFavorite(params);
        return new Result(ResultCode.SUCCESS,"取消收藏成功");
    }

    @Override
    public Result selFavorite(Map<String, Object> params) {
        if (params.isEmpty()||!params.containsKey("userId")||!params.containsKey("articleId")) {
            return new Result(ResultCode.ERROR,"参数错误");
        }
        UserArticle userArticle = userArticleDao.selFavorite(params);
       return new Result(ResultCode.SUCCESS,userArticle);
    }


}