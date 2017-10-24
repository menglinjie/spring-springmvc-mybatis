package com.mlj.service;

import com.mlj.modle.City;

import java.util.List;

public interface CityService {

    /**
     * 查找一级城市
     *
     * @return
     */
    public List<City> showF();

    public List<City> showS();

    /**
     * 根据父类id查找城市
     *
     * @param parentId
     * @return
     */
    public List<City> showByParentId(int parentId);
}
