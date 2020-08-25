package com.cdc.train.controller;

import com.cdc.train.entity.UserArticle;
import com.cdc.train.service.UserArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserArticle)表控制层
 *
 * @author makejava
 * @since 2020-08-25 15:41:51
 */
@RestController
@RequestMapping("userArticle")
public class UserArticleController {
    /**
     * 服务对象
     */
    @Resource
    private UserArticleService userArticleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserArticle selectOne(Integer id) {
        return this.userArticleService.queryById(id);
    }

}