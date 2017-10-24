package com.mlj.service;

import com.mlj.dao.OrderDao;
import com.mlj.modle.House;
import com.mlj.modle.Order_house;
import com.mlj.modle.order;
import com.mlj.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 查看房东订单（待审核 已审核）
     *
     * @param page
     * @param status
     * @param creatorId
     * @return
     */
    public PageBean getByD(int page, int status, String creatorId) {
        int count = orderDao.getCountD(status, creatorId);//总记录数
        int totalPage = PageBean.countTotalPage(4, count);//总页数
        int offset = PageBean.countOffset(4, page);//当前页开始记录
        int length = 4;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<Order_house> order_houses = orderDao.getListByD(offset, length, status, creatorId);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(order_houses, count, totalPage, currentPage, 4);
        pageBean.init();
        return pageBean;
    }

    /**
     * 查看房客订单（待审核 已审核）
     *
     * @param page
     * @param status
     * @param orderoId
     * @return
     */
    public PageBean getByK(int page, int status, String orderoId) {
        int count = orderDao.getCountK(status, orderoId);//总记录数
        int totalPage = PageBean.countTotalPage(4, count);//总页数
        int offset = PageBean.countOffset(4, page);//当前页开始记录
        int length = 4;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<Order_house> order_houses = orderDao.getListK(offset, length, status, orderoId);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(order_houses, count, totalPage, currentPage, 4);
        pageBean.init();
        return pageBean;
    }

    /**
     * 下订单
     *
     * @param order
     * @param order_house
     */
    public void save(order order, Order_house order_house) {
        orderDao.save(order);
        orderDao.save1(order_house);
    }

    /**
     * 受理订单 拒绝订单 删除订单
     *
     * @param status
     * @param id
     */
    public void updateStatus(int status, String id) {
        orderDao.updateStatus(id, status);
    }
}
