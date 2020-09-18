package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.entity.Type;
import com.cdc.train.service.TypeService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/queryAll")
    public Result queryAll(){
        List<Type> typeList = typeService.queryAll();
        if (CollectionUtils.isEmpty(typeList) || typeList.size() <= 0){
            return new Result(ResultCode.TYPE_NOT_FOUND);
        }
        return new Result(ResultCode.SUCCESS,typeList);
    }

}