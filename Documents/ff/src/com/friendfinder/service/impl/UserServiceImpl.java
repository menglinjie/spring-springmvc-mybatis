package com.friendfinder.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.friendfinder.model.Goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendfinder.dao.GoodsDao;
import com.friendfinder.dao.UserDao;
import com.friendfinder.model.User;
import com.friendfinder.model.UserAccount;
import com.friendfinder.model.UserExtension;
import com.friendfinder.model.UserQueryVo;
import com.friendfinder.model.UserShip;
import com.friendfinder.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao dao;
	//删除用户，改变用户状态
	@Override
	public boolean delete(Integer id) {
		int i = dao.delete(id);
		return i == 1 ? true : false;
		
	}
	//查找所有用户
	@Override
	public List<User> findAll() {
		List<User> findAllList = dao.findAll();
		return findAllList;
	} 
	//查看个人资料
	@Override
	public User findById(Integer id) {

		User user = dao.findById(id);

		return user;
	}
	//增加用户
	@Override
	public boolean add(Long phone,String password) {
		if(isExict(phone)){
			return false;
		}
		int i = dao.add(phone,password);
		return i == 1 ? true : false;
		
	}
	//更新用户
	
	@Override
	public boolean update(User user) {

		 int i = dao.update(user);
		 return i == 1 ? true : false;
	}
	//注册是否已经存在
	@Override
	public boolean isExict(Long phone) {
		User user = dao.isExict(phone);
		return user == null ? false : true;
	}

	//验证账号密码
	@Override
	public User isValid(Long phone, String password) {
		User user =  dao.isValid(phone, password);
		return user;
		 
	} 
	//根据条件查询用户
	@Override
	public List<User>findCondition(Date createTime,Double salary,Integer age) {
		List<User> findCondition = dao.findAll();
		return findCondition;
	}
	
	@Override
	public User selectByPhone(Long phone) {
		return dao.isExict(phone);
	}

	
	///好友系统
	@Override
	public void insertfans(Integer owner, Integer fans) {
		// TODO Auto-generated method stub
		 dao.insertfans(owner, fans);
	}
	@Override
	public void delectfans(Integer owner, Integer fans) {
		// TODO Auto-generated method stub
		 dao.delectfans(owner, fans);
	}
	@Override
	public List<UserShip> selectAllfans(Integer owner,Integer fans) {
		// TODO Auto-generated method stub
		List<UserShip> list = dao.selectAllfans(owner,fans);
		return list;
	}
	@Override
	public Integer selectFans(Integer userId,Integer fansId){
		return dao.selectfans(userId, fansId);
	}
	@Override
	public Integer[] selectFans(Integer owner){
		return  dao.selectFans(owner);
	}
	@Override
	public Integer[] selectFllow(Integer fans){
		return  dao.selectFllow(fans);
	}

	//财富系统
	@Override
	public void insertAccount(Integer userId) {
		 dao.insertAccount(userId);
	}
	@Override
	public void deleteAccount(Integer userId) {
		// TODO Auto-generated method stub
		 dao.deleteAccount(userId);
	}
	@Override
	public void updateAccount(UserAccount userAccount) {
		System.out.println(userAccount.getUserId());
		if (dao.selectAccount(userAccount.getUserId()) != null) {
			dao.updateAccount(userAccount);
		} else {
			dao.insertAccount(userAccount.getUserId());
		}		
	}
	@Override
	public UserAccount selectAccount(Integer userId) {
		return dao.selectAccount(userId);
	}
	
	//礼物拥有
	@Override
	public void insertGood(Integer id, Integer num, Integer userId) {
		// TODO Auto-generated method stub
		 dao.insertGood(id, num, userId);
	}
	@Override
	public void updateGood(Integer id, Integer value, Integer userId) {
		// TODO Auto-generated method stub
		try{
			if(dao.selectGoodNum(id, userId)>=0)
			dao.updateGood(id, value, userId);
		}catch (Exception e){
			dao.insertGood(id, value, userId);
		}
	}
	
	@Override
	public void deleteGood(Integer id, Integer userId) {
		dao.deleteGood(id, userId);
	}
	
	@Autowired
	private GoodsDao goodDao;
	@Override
	public Map<Goods, Integer> selectGoodsOwn(Integer userId) {
		// TODO Auto-generated method stub
		Integer[] goodsId = dao.selectGoodId(userId);
		Integer[] goodsNum = dao.selectGoodsNum(userId);
		Goods[] goods = new Goods[goodsId.length] ;
		for(int i=0;i<goodsId.length;i++){
			goods[i]=goodDao.selectGoodsById(goodsId[i]);
		}
		System.err.println("判断赠送礼物数量是否一致：");
		System.err.println(goods.length);
		System.err.println(goodsNum.length);
		
		Map<Goods, Integer> g = new HashMap<Goods, Integer>();
		for(int i=0;i<goodsId.length;i++){
			g.put(goods[i], goodsNum[i]);
		}
		
		return g;
	}
	@Override
	public List<UserExtension> listOrderUser(UserQueryVo vo) {
		return dao.listOrderUser(vo);
	}
	@Override
	public Integer countOrderUser(UserQueryVo vo) {
		return dao.countOrderUser(vo);
	}
	@Override
	public Integer[] selectGoodsNum(Integer userId) {
		return dao.selectGoodsNum(userId);
	}
	@Override
	public Integer selectGoodNum(Integer id, Integer userId) {
		return dao.selectGoodNum(id, userId);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
