package com.friendfinder.model;

/**
 * Created by 12191 on 2017/5/26/ 0026.
 *
 */
public class UserSynopsis {
    private Integer id;
    private String nickname;
    private String imgPath;
    private String introduce;

    public UserSynopsis() {}

    public UserSynopsis(Integer id, String nickname, String imgPath, String introduce) {
        this.id = id;
        this.nickname = nickname;
        this.imgPath = imgPath;
        this.introduce = introduce;
    }

    public Integer getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
