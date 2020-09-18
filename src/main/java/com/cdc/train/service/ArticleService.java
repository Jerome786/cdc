package com.cdc.train.service;

import com.cdc.train.common.Result;
import com.cdc.train.entity.dto.ArticleDTO;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 新增数据
     *
     * @param articleDTO 实例对象
     * @return 实例对象
     */
    Result insert(ArticleDTO articleDTO);

    /**
     * 新增图片
     *
     * @param imgFile 上传的图片文件
     * @return 实例对象
     */
    Result issueImages(MultipartFile imgFile);

    /**
     * 根据条件筛选获取所有的文章
     * @param map
     * @return
     */
    Result getAllByMap(Map<String, Object> map);
}