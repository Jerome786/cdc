package com.cdc.train.entity.dto;

import com.cdc.train.entity.Comment;

public class CommentDTO extends Comment {
    private String avatar;
    private String nickname;

    private String pUserId;

    private String pNickname;

    private String pcontent;

    private String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getpUserId() {
        return pUserId;
    }

    public void setpUserId(String pUserId) {
        this.pUserId = pUserId;
    }

    public String getpNickname() {
        return pNickname;
    }

    public void setpNickname(String pNickname) {
        this.pNickname = pNickname;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }
}
