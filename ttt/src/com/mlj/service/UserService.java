package com.mlj.service;

import com.mlj.modle.User;

public interface UserService {

    /**
     * 邮箱查重
     *
     * @param email
     * @return
     */
    public boolean show(String email);

    /**
     * 用户注册
     *
     * @param user
     */
    public void add(User user);
}
