package com.friendfinder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.friendfinder.model.Account;
import com.friendfinder.model.Goods;
import com.friendfinder.model.Manage;

public interface ManageDao {
	
	// 通过id查询管理员账户
	Manage selectById(Integer id);
	
	// 通过用户名和密码查询管理员账户
	Manage selectManage(@Param("name")String name, @Param("password")String password);
	
	// 用户全部用户查询
	List<Account> selectAllUser();
	
	// 查询所有商品信息
	List<Goods> selectAllGoods();
	
	List<Goods> selectAllGoodsForManage();
	
	// 更新博客状态
	void updateBlog(@Param("state") String state, @Param("id") Integer id );
	
	// 更新商品信息
	void updateGoods(Goods goods);
 }
