package com.mlj.dao;

import com.mlj.modle.City;

import java.util.List;

public interface CityDao {

    /**
     * 查找所有一级城市
     *
     * @return
     */
    public List<City> select();

    /**
     * 查找所有城市
     *
     * @return
     */
    public List<City> selectAll();

    /**
     * 根据父类id查找城市
     *
     * @param parentId
     * @return
     */
    public List<City> selectByParentId(int parentId);
}
