package com.cdc.train.entity;

import java.io.Serializable;

/**
 * (UserArticle)实体类
 *
 * @author makejava
 * @since 2020-08-25 15:41:50
 */
public class UserArticle implements Serializable {
    private static final long serialVersionUID = 927958885933478750L;

    private Integer id;

    private String userId;

    private Integer articleId;

    private Integer favorite;

    private Integer like;

    private String message;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}