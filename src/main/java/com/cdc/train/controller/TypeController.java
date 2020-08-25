package com.cdc.train.controller;

import com.cdc.train.entity.Type;
import com.cdc.train.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Type)表控制层
 *
 * @author makejava
 * @since 2020-08-25 15:38:44
 */
@RestController
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Resource
    private TypeService typeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Type selectOne(Integer id) {
        return this.typeService.queryById(id);
    }

}