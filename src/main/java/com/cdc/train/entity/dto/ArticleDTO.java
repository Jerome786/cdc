package com.cdc.train.entity.dto;

import com.cdc.train.entity.Article;

public class ArticleDTO  extends Article {
    private String pulisher;
    private String typeName;

    private String nickName;

    private Integer favoriteAmount;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getFavoriteAmount() {
        return favoriteAmount;
    }

    public void setFavoriteAmount(Integer favoriteAmount) {
        this.favoriteAmount = favoriteAmount;
    }

    public String getPulisher() {
        return pulisher;
    }

    public void setPulisher(String pulisher) {
        this.pulisher = pulisher;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
