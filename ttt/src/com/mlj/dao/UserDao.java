package com.mlj.dao;

import com.mlj.modle.User;

public interface UserDao {

    /**
     * 根据邮箱查找
     *
     * @param email
     * @return
     */
    public Integer select(String email);

    /**
     * 添加用户
     *
     * @param user
     */
    public void insert(User user);
}
