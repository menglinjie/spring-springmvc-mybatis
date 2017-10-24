package com.friendfinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendfinder.dao.ManageDao;
import com.friendfinder.model.Account;
import com.friendfinder.model.Goods;
import com.friendfinder.model.Manage;
import com.friendfinder.service.ManageService;

@Service("manageService")
public class ManageServiceImpl implements ManageService {

	@Autowired
	ManageDao dao;
	
	@Override
	public Manage selectById(Integer id) {
		return dao.selectById(id);
	}

	@Override
	public Manage selectManage(String name, String password) {
		return dao.selectManage(name, password);
	}

	@Override
	public List<Account> selectAllUser() {
		return dao.selectAllUser();
	}

	@Override
	public List<Goods> selectAllGoods() {
		return dao.selectAllGoods();
	}

	@Transactional
	@Override
	public void updateBlog(String state, Integer id) {
		dao.updateBlog(state, id);
	}

	@Transactional
	@Override
	public void updateGoods(Goods goods) {
		dao.updateGoods(goods);
	}

	@Override
	public List<Goods> selectAllGoodsForManage() {
		return dao.selectAllGoodsForManage();
	}

}
