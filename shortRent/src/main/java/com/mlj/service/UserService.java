package com.mlj.service;

import com.mlj.dao.UserDao;
import com.mlj.modle.User;
import com.mlj.util.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 分页查询
     *
     * @param pageSize
     * @param page
     * @return
     */
    public PageBean getPagen(int pageSize, int page) {
        int count = userDao.getCount();//总记录数
        int totalPage = PageBean.countTotalPage(pageSize, count);//总页数
        int offset = PageBean.countOffset(pageSize, page);//当前页开始记录
        int length = pageSize;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<User> users = userDao.getList(offset, length);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(users, count, totalPage, currentPage, pageSize);
        pageBean.init();
        return pageBean;
    }

    /**
     * 用户总数
     *
     * @param
     * @return
     */
    public int[] getCount() {
        int a = userDao.getCount();
        int b = userDao.getCount1();
        int[] counts = {a, b, a - b};
        return counts;
    }


    /**
     * 用户登录
     *
     * @param name
     * @param pass
     * @return
     */
    public User getByNameAndPass(String name, String pass) {
        if (StringUtils.isBlank(name) & StringUtils.isBlank(pass)) {
            return new User();
        }
        return userDao.getByNameAndPass(name, pass);
    }

    /**
     * 查找单个用户
     *
     * @param id
     * @return
     */
    public User get(String id) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id不存在");
        }
        return userDao.get(id);
    }

    /**
     * 获取加密后密码
     *
     * @param name
     * @return
     */
    public User getPass(String name) {
        return userDao.getPass(name);
    }

    /**
     * 注册修改用户
     *
     * @param user
     * @return
     */
    public User saveOrUpdate(User user) {
        userDao.save(user);
        return user;
    }

    /**
     * 冻结/解冻/删除用户
     *
     * @param id
     * @param status
     */
    public void updateStatus(String id, int status) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id为空");
        }
        userDao.updateStatus(id, status);
    }
}
