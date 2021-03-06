package com.cdc.train.service.impl;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.dao.CommentDao;
import com.cdc.train.entity.Comment;
import com.cdc.train.entity.dto.CommentDTO;
import com.cdc.train.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2020-08-25 15:37:53
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    @Override
    public Comment queryById(Integer commentId) {
        return this.commentDao.queryById(commentId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Comment> queryAllByLimit(int offset, int limit) {
        return this.commentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增评论
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insertComment(Comment comment) {
        this.commentDao.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDao.update(comment);
        return this.queryById(comment.getCommentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commentId) {
        return this.commentDao.deleteById(commentId) > 0;
    }

    /**
     * 文章id查询所有评论
     * @param comment
     * @return
     */
    @Override
    public List selectByArticleId(Comment comment) {
        List<CommentDTO> list = commentDao.selectByArticleId(comment);
        return list;
    }

    @Override
    public Result selParentComment(String userId) {
        return new Result(ResultCode.SUCCESS,commentDao.selParentComment(userId));
    }

    @Override
    public Result replay(String userId) {
        List<CommentDTO> resultList = new ArrayList<>();
        Comment comment = new Comment();
        comment.setUserId(userId);
        //获取我的评论
        List<Comment> commentList = commentDao.queryAll(comment);
        for (Comment comment1 : commentList) {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("commentParentId",comment1.getCommentId());
            resultList.addAll(commentDao.queryChildren(params));
        }
        return new Result(ResultCode.SUCCESS,resultList);
    }
}