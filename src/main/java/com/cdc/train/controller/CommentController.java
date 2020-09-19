package com.cdc.train.controller;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.entity.Comment;
import com.cdc.train.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2020-08-25 15:37:53
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    @RequestMapping("replay")
    public Result replay(@RequestParam String userId){
        if (StringUtils.isEmpty(userId)){
            return new Result(ResultCode.ERROR,"参数为空");
        }
        try {
            return commentService.replay(userId);
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 选出我发布文章的一级评论（除去我自己的评论）
     * @param userId
     * @return
     */
    @RequestMapping("selParentComment")
    public Result selParentComment(@RequestParam String userId){
        if (StringUtils.isEmpty(userId)){
            return new Result(ResultCode.ERROR,"参数为空");
        }
        try {
            return commentService.selParentComment(userId);
        }catch (Exception e){
            return new Result(ResultCode.ERROR, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param Id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/selectOne/{Id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Comment selectOne(@PathVariable("Id") Integer Id) {
        return this.commentService.queryById(Id);
    }

    /**
     * 保存
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public Object insertComment(@RequestBody Comment comment) {
        if (comment.getCommentId() == null || comment.getCommentId().equals("")) {
            if (comment.getContent() == null || comment.getContent().equals("")) {  //评论为空
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            commentService.insertComment(comment);
        } else {
            String content = comment.getContent();
            String oldContent = commentService.queryById(comment.getCommentId()).getContent();
            if (content.equals(oldContent)) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            commentService.update(comment);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除
     *
     * @param Id
     * @return
     */
    @RequestMapping(value = "/delete/{Id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object deleteCommentById(@PathVariable("Id") Integer Id) {
        if (this.commentService.deleteById(Id)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查看文章下的ID
     *
     * @param params
     * @return
     */
    @RequestMapping("/selectByArticleId")
    public Result selectByArticleId(@RequestBody Map<String, Object> params) {
        if (params.isEmpty() || !params.containsKey("articleId")) {
            return new Result(ResultCode.ERROR, "参数异常");
        }
        Integer articleId = (Integer) params.get("articleId");
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        List list = commentService.selectByArticleId(comment);
        return new Result(ResultCode.SUCCESS, list);
    }
}