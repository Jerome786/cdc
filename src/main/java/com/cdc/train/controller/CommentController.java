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
    @RequestMapping(value = "/selectOne", method = { RequestMethod.GET, RequestMethod.POST })
    public Comment selectOne(Integer id) {
        return this.commentService.queryById(id);
    }

    /**
     * 保存
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<Void> insertComment(@RequestBody Comment comment){
        if (comment.getCommentId()==null||comment.getCommentId().equals("")){
            if (comment.getContent()==null||comment.getContent()==""){  //评论为空
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            commentService.insertComment(comment);
        }else {
            commentService.update(comment);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<Void> deleteCommentById(Integer id) {
        if (this.commentService.deleteById(id)) {
            return  ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查看文章下的ID
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/selectByArticleId", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<List> selectByArticleId(Integer articleId) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        List list=commentService.selectByArticleId(comment);
        return ResponseEntity.ok(list);
    }
}