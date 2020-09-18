package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.entity.dto.ArticleDTO;
import com.cdc.train.service.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2020-08-25 15:25:28
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @PostMapping("issueArticle")
    public Result issueArticle(@RequestBody ArticleDTO articleDTO) {

        if (StringUtils.isEmpty(articleDTO.getUserId())) {
            return new Result(ResultCode.USER_IS_NOT_FOUND, "获取用户信息失败，请重新登录");
        }
        if (StringUtils.isEmpty(articleDTO.getTitle())) {
            return new Result(ResultCode.SYSTEM_ERROR, "标题信息为空");
        }
        return this.articleService.insert(articleDTO);
    }

    /**
     * 上传图片
     *
     * @param imgFile
     * @return
     */
    @RequestMapping("issueImages")
    public Result issueImages(@RequestParam("imgFile") MultipartFile imgFile) {

        return articleService.issueImages(imgFile);
    }

    /**
     * 带条件查询列表
     *
     * @param params
     * @return
     */
    @PostMapping("list")
    public Result list(@RequestBody Map<String, Object> params) {

        String userId = params.get("userId") != null ? params.get("userId").toString() : "";

        if (userId.equals("")) {
            return new Result(ResultCode.USER_IS_NOT_FOUND, "获取用户信息失败，请重新登录");
        }
        return articleService.getAllByMap(params);
    }

    @RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Result getImage(@RequestBody String imgList) throws IOException {

        Map<String, Object> map = new HashMap<>();

        if (!StringUtils.isEmpty(imgList)) {
            String[] imgArr = imgList.split(",");

            for (int i = 0; i < imgArr.length; i++) {
                File file = new File(imgArr[i]);
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
                map.put("img" + i, bytes);
            }
        }
        return new Result(ResultCode.SUCCESS, map);
    }

    @RequestMapping("selFavorite")
    public Result selFavorite (@RequestBody Map<String,Object> params){
        try {
            return articleService.selFavorite(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    /**
     * 取消收藏
     * @param params
     * @return
     */
    @RequestMapping("delFavorite")
    public Result delFavorite(@RequestBody Map<String,Object> params){
        try {
            return articleService.delFavorite(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    /**
     * 收藏
     */
    @RequestMapping("addFavorite")
    public Result addFavorite(@RequestBody Map<String,Object> params){
        try {
            return articleService.addFavorite(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("queryById")
    public Result queryById(@RequestParam("id") int id) {
        if (StringUtils.isEmpty(id) || id==0){
            return new Result(ResultCode.ERROR,"id为空");
        }
        try {
            return articleService.queryById(id);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }
    /**
     * 通过主键查询单条数据
     *
     * @param params 主键
     * @return 单条数据
     */
    @GetMapping("queryByUserId")
    public Result queryByUserId(@RequestBody  Map<String,Object> params) {
        if (params.isEmpty()||!params.containsKey("userId")){
            return new Result(ResultCode.ERROR,"userId为空");
        }
        try {
            return articleService.queryAllByLimit(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }

    /**
     * 查看我的收藏
     * @param params
     * @return
     */
    @RequestMapping("selFavoriteByUserId")
    public Result selFavoriteByUserId(@RequestBody Map<String,Object> params){
        if (params.isEmpty()||!params.containsKey("userId")){
            return new Result(ResultCode.ERROR,"userId为空");
        }
        try {
            return articleService.selFavoriteByUserId(params);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, "保存失败：" + e.getMessage());
        }
    }


}