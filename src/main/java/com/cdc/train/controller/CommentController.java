package com.cdc.train.controller;

import com.cdc.train.entity.Comment;
import com.cdc.train.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/selectOne/{Id}", method = { RequestMethod.GET, RequestMethod.POST })
    public Comment selectOne(@PathVariable("Id") Integer Id) {
        return this.commentService.queryById(Id);
    }

    /**
     * 保存
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    public Object insertComment(@RequestBody Comment comment){
        if (comment.getCommentId()==null||comment.getCommentId().equals("")){
            if (comment.getContent()==null||comment.getContent().equals("")){  //评论为空
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            commentService.insertComment(comment);
        }else {
            String content = comment.getContent();
            String oldContent = commentService.queryById(comment.getCommentId()).getContent();
            if (content.equals(oldContent)){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            commentService.update(comment);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{Id}", method = { RequestMethod.GET, RequestMethod.POST })
    public Object deleteCommentById(@PathVariable("Id") Integer Id) {
        if (this.commentService.deleteById(Id)) {
            return  ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查看文章下的ID
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/selectByArticleId/{articleId}", method = { RequestMethod.GET, RequestMethod.POST })
    public Object selectByArticleId(@PathVariable("articleId") Integer articleId) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        List list=commentService.selectByArticleId(comment);
        return ResponseEntity.ok(list);
    }
}