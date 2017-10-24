package com.friendfinder.dao;

import com.friendfinder.model.Goods;

public interface GoodsDao {
	
	//添加一个商品
	public void insertGoods(Goods goods);
	//删除一件商品
	public void deleteGoods(Goods goods);
	//更新商品
	public void updateGoods(Goods goods);
	//通过名字查询一件商品
	public Goods selectGoodsByName(String name);
	//
	public Goods selectGoodsById(Integer id);
	//查询所有商品
	public Goods[] selectAllGoods();
	
}
