package com.friendfinder.service.impl;

import com.friendfinder.dao.GoodsDao;
import com.friendfinder.model.Goods;
import com.friendfinder.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 12191 on 2017/5/10/ 0010.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public void addGoods(Goods goods){
        goodsDao.insertGoods(goods);

    };
    @Override
    public void deleteGoods(Goods goods){
        goodsDao.deleteGoods(goods);
    };
    @Override
    public void updateGoods(Goods goods){
        System.out.println(goods);
        goodsDao.updateGoods(goods);
    };
    @Override
    public Goods selectGoodsByName(String name){
        return goodsDao.selectGoodsByName(name);
    };
    @Override
    public Goods selectGoodsById(Integer id){
        return goodsDao.selectGoodsById(id);
    };
    @Override
    public Goods[] selectAllGoods(){
        return goodsDao.selectAllGoods();
    };

}
