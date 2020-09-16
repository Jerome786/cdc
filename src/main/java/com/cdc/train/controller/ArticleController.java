package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.service.ArticleService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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