package com.mlj.service.impl;

import com.mlj.dao.CityDao;
import com.mlj.modle.City;
import com.mlj.service.CityService;
import com.mlj.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class CityServiceImpl implements CityService {

    SqlSession sqlSession = MybatisUtil.getSqlSession(true);
    CityDao cityDao = sqlSession.getMapper(CityDao.class);

    @Override
    public List<City> showF() {
        return cityDao.select();
    }

    @Override
    public List<City> showS() {
        List<City> cities = cityDao.selectAll();
        List<City> citieS = new LinkedList<>();
        for (City c : cities
                ) {
            if (c.getParentId() != 0) {
                citieS.add(c);//二级菜单
            }
        }
        return citieS;
    }

    @Override
    public List<City> showByParentId(int parentId) {
        return cityDao.selectByParentId(parentId);
    }
}
