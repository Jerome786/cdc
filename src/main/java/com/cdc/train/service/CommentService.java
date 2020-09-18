package com.cdc.train.service;

import com.cdc.train.entity.Comment;
import com.cdc.train.entity.dto.CommentDTO;

import java.util.List;

/**
 * 评论服务接口
 *
 * @author makejava
 * @since 2020-08-25 15:37:53
 */
public interface CommentService {

    /**
     * 评论ID查询条评论
     *
     * @param commentId 主键
     * @return 实例对象
     */
    Comment queryById(Integer commentId);

    /**
     * 查询全部评论
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(int offset, int limit);

    /**
     * 新增评论
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insertComment(Comment comment);

    /**
     * 修改评论
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除评论
     *
     * @param commentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commentId);

    List<CommentDTO> selectByArticleId(Comment comment);
}