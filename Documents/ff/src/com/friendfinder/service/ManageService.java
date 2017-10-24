package com.friendfinder.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.friendfinder.model.Account;
import com.friendfinder.model.Goods;
import com.friendfinder.model.Manage;

public interface ManageService {
	
	// 通过id查询管理员账户
	Manage selectById(Integer id);
		
	// 通过用户名和密码查询管理员账户
	Manage selectManage(String name, String password);
	
	List<Account> selectAllUser();
	
	List<Goods> selectAllGoods();
	
	List<Goods> selectAllGoodsForManage();
	
	void updateBlog(String state, Integer id );
	
	void updateGoods(Goods goods);
}
