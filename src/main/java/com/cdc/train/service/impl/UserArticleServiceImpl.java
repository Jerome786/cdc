package com.cdc.train.service.impl;

import com.cdc.train.dao.UserArticleDao;
import com.cdc.train.entity.UserArticle;
import com.cdc.train.service.UserArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserArticle)表服务实现类
 *
 * @author makejava
 * @since 2020-08-25 15:41:51
 */
@Service("userArticleService")
public class UserArticleServiceImpl implements UserArticleService {
    @Resource
    private UserArticleDao userArticleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserArticle queryById(Integer id) {
        return this.userArticleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserArticle> queryAllByLimit(int offset, int limit) {
        return this.userArticleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userArticle 实例对象
     * @return 实例对象
     */
    @Override
    public UserArticle insert(UserArticle userArticle) {
        this.userArticleDao.insert(userArticle);
        return userArticle;
    }

    /**
     * 修改数据
     *
     * @param userArticle 实例对象
     * @return 实例对象
     */
    @Override
    public UserArticle update(UserArticle userArticle) {
        this.userArticleDao.update(userArticle);
        return this.queryById(userArticle.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userArticleDao.deleteById(id) > 0;
    }
}