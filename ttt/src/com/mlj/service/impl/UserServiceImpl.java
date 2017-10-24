package com.mlj.service.impl;

import com.mlj.dao.UserDao;
import com.mlj.modle.User;
import com.mlj.service.UserService;
import com.mlj.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {

    //实例化session
    SqlSession sqlSession = MybatisUtil.getSqlSession(true);
    UserDao userDao = sqlSession.getMapper(UserDao.class);

    @Override
    public boolean show(String email) {
        Integer count = userDao.select(email);
        if (count == 0) {
            return true;//邮箱唯一
        } else {
            return false;
        }
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }
}
