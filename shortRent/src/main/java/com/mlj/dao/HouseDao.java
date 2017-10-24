package com.mlj.dao;

import com.mlj.modle.House;
import com.mlj.modle.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HouseDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查看所有房屋（分页）状态
     *
     * @return
     */
    public List<House> getList(int offset, int length, int status) {
        String hql = "from House where status = ? order by createTime desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status);
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 所有房屋数量
     * 状态 1:未审核  2：已审核(未发布)  3：已发布 0：删除
     *
     * @return
     */
    public int getCount(int status) {
        String hql = "select count (*) from House where status = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 查看所有房屋(无状态)
     *
     * @return
     */
    public List<House> getAllListNoState(int offset, int length) {
        String hql = "from House order by createTime desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 所有房屋数量
     *
     * @return
     */
    public int getAllCountNoState() {
        String hql = "select count (*) from House";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 查看该房东所有房屋（分页）
     *
     * @return
     */
    public List<House> getListByUser(int offset, int length, int status, String creatorId) {
        String hql = "from House where status = ? and creator.id = ? order by createTime desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status);
        query.setString(1, creatorId);
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 所有房东房屋数量
     * 状态 1:未审核  2：已审核(未发布)  3：已发布 0：删除
     *
     * @return
     */
    public int getCountByUser(int status, String creatorId) {
        String hql = "select count (*) from House where status = ? and creator.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, status);
        query.setString(1, creatorId);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 查看房东所有房屋
     *
     * @param creatorId
     * @param offset
     * @param length
     * @return
     */
    public List<House> getAllHouse(String creatorId, int offset, int length) {
        String hql = "from House where creator.id = ? order by createTime desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, creatorId);
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 查看房东所有房屋数量
     *
     * @param creatorId
     * @return
     */
    public int getAllCount(String creatorId) {
        String hql = "select count (*) from House where creator.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, creatorId);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 条件查询房屋(已发布的房屋)
     *
     * @param param
     * @param offset
     * @param length
     * @return
     */
    public List<House> getListByParam(String param, int offset, int length) {
        String hql = "from House where status = 3 and (name like ? or describ like  ? or address like ?) order by createTime desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, "%" + param + "%");
        query.setString(1, "%" + param + "%");
        query.setString(2, "%" + param + "%");
        query.setMaxResults(length);
        query.setFirstResult(offset);
        return query.list();
    }

    /**
     * 条件查询房屋数量
     *
     * @param param
     * @return
     */
    public int getCountByParam(String param) {
        String hql = "select count (*) from House  where status = 3 and (name like ? or describ like  ? or address like ?)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, "%" + param + "%");
        query.setString(1, "%" + param + "%");
        query.setString(2, "%" + param + "%");
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 查询单个房屋
     *
     * @param
     * @return
     */
    public House get(String id) {
        String hql = "from House where id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, id);
        return (House) query.uniqueResult();
    }

    /**
     * 添加房屋
     *
     * @param house
     * @return
     */
    public House insert(House house) {
        sessionFactory.getCurrentSession().save(house);
        return house;
    }

    /**
     * 修改房屋信息
     *
     * @param house
     * @return
     */
    public House update(House house) {
        sessionFactory.getCurrentSession().update(house);
        return house;
    }

    /**
     * 修改状态（发布 审核 删除）
     * 状态 1:未审核  2：已审核(未发布)  3：已发布 0：删除
     *
     * @param id
     * @param status
     */
    public void updateStatus(String id, int status) {
        House house = this.get(id);
        house.setStatus(status);
        sessionFactory.getCurrentSession().update(house);
    }

}
