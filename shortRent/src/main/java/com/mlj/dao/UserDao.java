package com.mlj.dao;

import com.mlj.modle.User;
import com.mlj.util.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * 查询所有用户（分页）
     * 1:未冻结 0：冻结 -1：删除
     *
     * @param offset
     * @param length
     * @return
     */
    public List<User> getList(int offset, int length) {
        String hql = "from User";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 用户总数
     * 1:未冻结 0：冻结 -1：删除
     *
     * @return
     */
    public int getCount() {
        String hql = "select count(*) from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 未冻结用户数量
     *
     * @return
     */
    public int getCount1() {
        String hql = "select count(*) from User where status = 1";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 根据用户名和密码查询用户
     *
     * @param name
     * @param password
     * @return
     */
    public User getByNameAndPass(String name, String password) {
        String hql = "from User u where u.name = ? and u.password = ? and u.status = 1";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, name);
        query.setString(1, password);
        return (User) query.uniqueResult();
    }

    /**
     * 查询单个用户
     *
     * @param id
     * @return
     */
    public User get(String id) {
        String hql = "from User where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);
        return (User) query.uniqueResult();
    }

    /**
     * 获取加密后密码
     *
     * @param name
     * @return
     */
    public User getPass(String name) {
        String hql = "from User u where u.name = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, name);
        return (User) query.uniqueResult();
    }

    /**
     * 添加或修改用户
     *
     * @param user
     */
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     */
    public void updateStatus(String id, int status) {
        User user = this.get(id);
        user.setStatus(status);
        sessionFactory.getCurrentSession().update(user);
    }

}
