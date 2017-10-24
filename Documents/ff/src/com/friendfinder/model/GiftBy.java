package com.friendfinder.model;

/**
 * Created by 12191 on 2017/5/25/ 0025.
 */
public class GiftBy {

    public Integer userId;
    public GiftByType[] list;

    public GiftBy() {
    }

    public GiftBy(Integer userId, GiftByType[] list) {
        this.userId = userId;
        this.list = list;
    }

    public Integer getUserId() {
        return userId;
    }

    public GiftByType[] getList() {
        return list;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setList(GiftByType[] list) {
        this.list = list;
    }
}
