package com.mlj.dao;

import com.mlj.modle.Admin;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 管理员登录
     *
     * @param account
     * @param pass
     * @return
     */
    public Admin get(String account, String pass) {
        String hql = "from Admin where account = ? and password = ?";
        org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, account);
        query.setString(1, pass);
        return (Admin) query.uniqueResult();
    }
}
