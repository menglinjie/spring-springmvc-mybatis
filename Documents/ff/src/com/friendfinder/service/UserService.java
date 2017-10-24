package com.friendfinder.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.friendfinder.model.Goods;
import com.friendfinder.model.User;
import com.friendfinder.model.UserAccount;
import com.friendfinder.model.UserExtension;
import com.friendfinder.model.UserQueryVo;
import com.friendfinder.model.UserShip;

public interface UserService {
	//添加用户
	public boolean add(Long phone, String password);
	//更新资料
	public boolean update(User user);
	//删除用户,在数据库中改变用户状态
	public boolean delete(Integer id);
	//查看个人资料
	public User findById(Integer id);
	//查看所有用户
	public List<User> findAll();
	//注册时看是否已经注册
	public boolean isExict(Long phone);
	// 查找指定phone的人员信息
	public User selectByPhone(Long phone);
	//登录验证账号密码
	public User isValid(Long phone,String password);
	//根据条件查找用户   条件为：注册时间，收入，年龄，婚姻状况，学历等！！
	public List<User> findCondition(Date createTime,Double salary,Integer age);
	// 查询指定条件的用户
	public List<UserExtension> listOrderUser(UserQueryVo vo);
	// 查询指定条件的总人数 
	public Integer countOrderUser(UserQueryVo vo);
	//======好友系统=======
	public void insertfans(Integer owner,Integer fans);
	public void  delectfans(Integer owner,Integer fans);
	public List<UserShip> selectAllfans(Integer owner,Integer fans);
	public Integer selectFans(Integer userId,Integer fansId);
	public Integer[] selectFans(Integer owner);
	public Integer[] selectFllow(Integer fans);
	//========财富系统=============
	public void insertAccount(Integer userId);
	public void deleteAccount(Integer userId);
	public void updateAccount(UserAccount userAccount);
	public UserAccount selectAccount(Integer userId);
	
	//========礼物拥有========
	public void insertGood(Integer id,Integer num,Integer userId);
	public void updateGood(Integer id,Integer value,Integer userId);
	// 删除用户礼物记录
	public void deleteGood(Integer id, Integer userId);
	public Map<Goods, Integer> selectGoodsOwn(Integer userId);
	public Integer[] selectGoodsNum(Integer userId);
	public Integer selectGoodNum(Integer id, Integer userId);
}