package com.cdc.train.entity.dto;

import com.cdc.train.entity.Article;

public class ArticleDTO  extends Article {
    private String pulisher;
    private String typeName;

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
