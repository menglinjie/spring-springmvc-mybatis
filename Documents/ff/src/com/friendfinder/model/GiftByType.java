package com.friendfinder.model;

/**
 * Created by 12191 on 2017/5/25/ 0025.
 */
public class GiftByType {
    public Integer id;
    public Integer num;
    public Integer type;

    public GiftByType() {
    }

    public GiftByType(Integer id, Integer num, Integer type) {
        this.id = id;
        this.num = num;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNum() {
        return num;
    }

    public Integer getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
