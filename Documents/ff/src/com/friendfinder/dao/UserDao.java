package com.friendfinder.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.friendfinder.model.User;
import com.friendfinder.model.UserAccount;
import com.friendfinder.model.UserExtension;
import com.friendfinder.model.UserQueryVo;
import com.friendfinder.model.UserShip;

public interface UserDao {
		//添加用户
		public int add(@Param("phone")Long phone,@Param("password")String password);
		//更新资料
		public int update(User user);
		//删除用户,在数据库中改变用户状态
		public int delete(Integer id);
		//查看个人资料
		public User findById(Integer id);
		//查看所有用户
		public List<User> findAll();
		//注册时看是否已经注册
		public User isExict(@Param("phone")Long phone);
		//登录验证账号密码
		public User isValid(@Param("phone")Long phone,@Param("password")String password);
		//根据条件查找用户   条件为：注册时间，收入，年龄，婚姻状况，学历等！！
		public List<User> findCondition(Date createTime,Double salary,Integer age);
		// 查询指定条件的用户
		public List<UserExtension> listOrderUser(@Param("vo")UserQueryVo vo);
		// 查询指定条件的总人数
		public Integer countOrderUser(@Param("vo")UserQueryVo vo);
		
		//======好友系统=======
		public void insertfans(@Param("owner") Integer owner,@Param("fans") Integer fans);
		public void delectfans(@Param("owner") Integer owner,@Param("fans") Integer fans);
		public List<UserShip> selectAllfans(@Param("owner") Integer owner ,@Param("fans")Integer fans);
		public Integer selectfans(@Param("owner")Integer owner, @Param("fans")Integer fans);
		public Integer[] selectFans(@Param("owner")Integer owner);
		public Integer[] selectFllow(@Param("fans")Integer fans);
		//========财富系统=============
		public void insertAccount(Integer userId);
		public void deleteAccount(Integer userId);
		public void updateAccount(UserAccount userAccount);
		public Integer selAct(Integer userId);
		public UserAccount selectAccount(Integer userId);
		
		//========礼物拥有=========
		public void insertGood(@Param("id") Integer id,@Param("num") Integer num,@Param("userId")Integer userId);
		public void updateGood(@Param("id") Integer id,@Param("num") Integer num,@Param("userId")Integer userId);
		public void deleteGood(@Param("id") Integer id,@Param("userId")Integer userId);
		public Integer[] selectGoodId(Integer userId);
		public Integer[] selectGoodsNum(Integer userId);
		public Integer selectGoodNum(@Param("id")Integer id,@Param("userId")Integer userId);

}
