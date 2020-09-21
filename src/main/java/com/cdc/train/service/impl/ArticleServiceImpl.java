package com.cdc.train.service.impl;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.dao.ArticleDao;
import com.cdc.train.dao.UserArticleDao;
import com.cdc.train.entity.Article;
import com.cdc.train.entity.UserArticle;
import com.cdc.train.entity.dto.ArticleDTO;
import com.cdc.train.service.ArticleService;
import com.cdc.train.utils.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    /**
     * 新增数据
     *
     * @param articleDTO 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(ArticleDTO articleDTO) {

        Date date = new Date();
        articleDTO.setCreateTime(date);
        int insertResult = this.articleDao.insert(articleDTO);
        if (articleDTO.getCondition().equals("0")) {
            if (insertResult > 0) {
                return new Result(ResultCode.SUCCESS, "已成功存入草稿");
            } else {
                return new Result(ResultCode.SYSTEM_ERROR);
            }
        } else if (articleDTO.getCondition().equals("1")) {
            if (insertResult > 0) {
                return new Result(ResultCode.SUCCESS, "发布成功，待审核");
            } else {
                return new Result(ResultCode.SYSTEM_ERROR);
            }
        } else {
            return new Result(ResultCode.SYSTEM_ERROR, "发布操作失败");
        }
    }

    @Override
    public Result issueImages(MultipartFile imgFile) {

        //上传图片
        //当图片文件为空时
        if (!imgFile.isEmpty()) {

            // 拿到文件名及后缀名
            String filename = imgFile.getOriginalFilename();
            String subString = filename.substring(filename.lastIndexOf(".")+1);

            //判断文件后缀名
            //常见的图片格式有bmp，jpg，png，tif，gif，pcx，tga，exif，fpx，svg，psd，cdr，pcd，dxf，ufo，eps，ai，raw，WMF，webp等
            List<String> imgFormat = Arrays.asList("bmp", "jpg", "png", "tif", "gif", "svg", "tga", "exif", "fpx", "pcx", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "WMF", "webp");
            if (!imgFormat.contains(subString)) {
                return new Result(ResultCode.SYSTEM_ERROR, "图片格式不对");
            }

            // 存放上传图片的文件夹
            File fileDir = UploadUtil.getImgDirFile();

            // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
            System.out.println(fileDir.getAbsolutePath());

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
                System.out.println(newFile.getAbsolutePath());

                // 上传图片到 -》 “绝对路径”
                imgFile.transferTo(newFile);
                return new Result(ResultCode.SUCCESS, newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Result(ResultCode.ERROR, "上传图片失败");
    }

    @Override
    public Result getAllByMap(Map<String, Object> map) {

        String typeId = map.get("typeId") != null ? map.get("typeId").toString() : "";
//        String titleAndContent = map.get("titleAndContent") != null ? map.get("titleAndContent").toString() : "";

        if (typeId.equals("")) {
            return new Result(ResultCode.ERROR, "类型加载失败，请刷新页面");
        }
        if (typeId.equals("0")) {
            map.put("typeId", null);
        }
        List<ArticleDTO> articleAll = this.articleDao.getAllByMap(map);

        HashMap<String, Object> data = new HashMap<>();
        if (articleAll != null) {
            data.put("articleAll", articleAll);
        }
        return new Result(ResultCode.SUCCESS, data);
    }

    @Override
    @Transactional
    public Result delArticle(Integer articleId) {
        Article article = articleDao.queryById(articleId);
        if (article == null){
            return new Result(ResultCode.ERROR,"不存在该文章");
        }
        articleDao.deleteById(articleId);
        userArticleDao.deleteByArticleId(articleId);
        return  new Result(ResultCode.SUCCESS,"成功删除");
    }


}