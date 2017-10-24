package com.mlj.service;

import com.mlj.dao.HouseDao;
import com.mlj.modle.House;
import com.mlj.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseDao houseDao;

    /**
     * 分页查询所有房屋(不同状态)
     *
     * @param page
     * @param status
     * @return
     */
    public PageBean getList(int page, int status) {
        int count = houseDao.getCount(status);//总记录数
        int totalPage = PageBean.countTotalPage(4, count);//总页数
        int offset = PageBean.countOffset(4, page);//当前页开始记录
        int length = 4;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<House> houses = houseDao.getList(offset, length, status);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(houses, count, totalPage, currentPage, 4);
        pageBean.init();
        return pageBean;
    }

    /**
     * 查询所有房屋数量（不同状态）1:未审核  2：已审核(未发布)  3：已发布
     *
     * @param status
     * @return
     */
    public int getCount(int status) {
        return houseDao.getCount(status);
    }

    /**
     * 分页查询所有房屋（全部状态）
     *
     * @param page
     * @return
     */
    public PageBean getAllListNoState(int page) {
        int count = houseDao.getAllCountNoState();//总记录数
        int totalPage = PageBean.countTotalPage(4, count);//总页数
        int offset = PageBean.countOffset(4, page);//当前页开始记录
        int length = 4;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<House> houses = houseDao.getAllListNoState(offset, length);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(houses, count, totalPage, currentPage, 4);
        pageBean.init();
        return pageBean;
    }

    /**
     * 查询所有房屋数量（不同状态）1:未审核  2：已审核(未发布)  3：已发布
     *
     * @return
     */
    public int getCountNoState() {
        return houseDao.getAllCountNoState();
    }


    /**
     * 房屋统计
     *
     * @return
     */
    public int[] getTotleCount() {
        int a = this.getCount(1);//未审核
        int b = this.getCount(2);//已审核
        int c = this.getCount(3);//已发布
        int d = this.getCount(0);//被拒绝
        int e = a + b + c + d;//总数
        int[] totleHouse = {e, a, b, c, d};
        return totleHouse;
    }

    /**
     * 查看房东房屋（不同状态）
     *
     * @param page
     * @param status
     * @param creatorId
     * @return
     */
    public PageBean getListByUser(int page, int status, String creatorId) {
        int count = houseDao.getCountByUser(status, creatorId);//总记录数
        int totalPage = PageBean.countTotalPage(4, count);//总页数
        int offset = PageBean.countOffset(4, page);//当前页开始记录
        int length = 4;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<House> houses = houseDao.getListByUser(offset, length, status, creatorId);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(houses, count, totalPage, currentPage, 4);
        pageBean.init();
        return pageBean;
    }

    /**
     * 查看房东屋数量(不同状态）
     *
     * @param status
     * @param creatorId
     * @return
     */
    public int getCountByUser(int status, String creatorId) {
        return houseDao.getCountByUser(status, creatorId);
    }


    /**
     * 查看房东房wu
     *
     * @param page
     * @param creatorId
     * @return
     */
    public PageBean getAllByUser(int page, String creatorId) {
        int count = houseDao.getAllCount(creatorId);//总记录数
        int totalPage = PageBean.countTotalPage(4, count);//总页数
        int offset = PageBean.countOffset(4, page);//当前页开始记录
        int length = 4;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<House> houses = houseDao.getAllHouse(creatorId, offset, length);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(houses, count, totalPage, currentPage, 4);
        pageBean.init();
        return pageBean;
    }

    /**
     * 查看房东屋数
     *
     * @param creatorId
     * @return
     */
    public int getAllCountByUser(String creatorId) {
        return houseDao.getAllCount(creatorId);
    }

    /**
     * 搜索房屋（已发布）
     *
     * @param page
     * @param param
     * @return
     */
    public PageBean getListByParam(int page, String param) {
        int count = houseDao.getCountByParam(param);//总记录数
        int totalPage = PageBean.countTotalPage(2, count);//总页数
        int offset = PageBean.countOffset(2, page);//当前页开始记录
        int length = 2;//每页记录数
        int currentPage = PageBean.countCurrentPage(page);//当前页
        List<House> houses = houseDao.getListByParam(param, offset, length);//该页记录
        //把分页记录保存到bean
        PageBean pageBean = new PageBean(houses, count, totalPage, currentPage, 2);
        pageBean.init();
        return pageBean;
    }

    /**
     * 搜索房屋总数
     *
     * @param param
     * @return
     */
    public int getCountByParam(String param) {
        return houseDao.getCountByParam(param);
    }

    /**
     * 查找单个房屋
     *
     * @param id
     * @return
     */
    public House get(String id) {
        return houseDao.get(id);
    }

    /**
     * 创建房屋
     *
     * @param house
     * @return
     */
    public House save(House house) {
        houseDao.insert(house);
        return house;
    }

    /**
     * 修改房屋信息
     *
     * @param house
     * @return
     */
    public House update(House house) {
        return houseDao.update(house);
    }

    /**
     * 修改房屋状态（审核/发布/删除）
     *
     * @param id
     * @param status
     */
    public void updateStatus(String id, int status) {
        houseDao.updateStatus(id, status);
    }
}
