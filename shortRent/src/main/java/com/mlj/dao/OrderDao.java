package com.mlj.dao;

import com.mlj.modle.Order_house;
import com.mlj.modle.order;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {

    @Autowired
    private SessionFactory sessionFactory;


    /**
     * 查询房东订单（1：已受理 2：已拒绝 0：未受理 -1:已删除）
     *
     * @param offset
     * @param length
     * @param status
     * @param creatorId
     * @return
     */
    public List<Order_house> getListByD(int offset, int length, int status, String creatorId) {
        String hql = "from Order_house oh where oh.order.status = ? and oh.house.creator.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status);
        query.setString(1, creatorId);
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 查询房东订单数量
     *
     * @param status
     * @param creatorId
     * @return
     */
    public int getCountD(int status, String creatorId) {
        String hql = "select count (*) from Order_house oh where oh.order.status = ? and oh.house.creator.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status);
        query.setString(1, creatorId);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 查询房客订单（1：已受理 2：已拒绝 0：未受理 -1:已删除）
     *
     * @param offset
     * @param length
     * @param status
     * @param orderoId
     * @return
     */
    public List<Order_house> getListK(int offset, int length, int status, String orderoId) {
        String hql = "from Order_house oh where oh.order.ordero.id = ? and oh.order.status = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, orderoId);
        query.setInteger(1, status);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        return query.list();
    }

    /**
     * 查询房客订单数量
     *
     * @param status
     * @param orderoId
     * @return
     */
    public int getCountK(int status, String orderoId) {
        String hql = "select count (*) from Order_house oh where oh.order.ordero.id = ? and oh.order.status = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, orderoId);
        query.setInteger(1, status);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 添加订单
     *
     * @param order
     */
    public void save(order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    /**
     * 添加订单房屋关系
     *
     * @param order_house
     */
    public void save1(Order_house order_house) {
        sessionFactory.getCurrentSession().save(order_house);
    }

    /**
     * 查找单个订单
     *
     * @param id
     * @return
     */
    public order get(String id) {
        String hql = "from order where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);
        return (order) query.uniqueResult();
    }

    /**
     * 修改订单状态
     *
     * @param id
     * @param status
     */
    public void updateStatus(String id, int status) {
        order order = this.get(id);
        System.out.println(order.getNumber()+"================="+status);
        order.setStatus(status);
        sessionFactory.getCurrentSession().update(order);
    }
}
