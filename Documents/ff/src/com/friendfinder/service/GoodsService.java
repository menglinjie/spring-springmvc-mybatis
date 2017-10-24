package com.friendfinder.service;


import com.friendfinder.dao.GoodsDao;
import com.friendfinder.model.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 12191 on 2017/5/10/ 0010.
 */

public interface   GoodsService {

    public void addGoods(Goods goods);
    public void deleteGoods(Goods goods);
    public void updateGoods(Goods goods);
    public Goods selectGoodsByName(String name);
    public Goods selectGoodsById(Integer id);
    public Goods[] selectAllGoods();
}
