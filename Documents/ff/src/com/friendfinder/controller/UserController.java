package com.friendfinder.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.friendfinder.listener.SessionListener;
import com.friendfinder.model.*;
import com.friendfinder.service.AttachmentService;
import com.friendfinder.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import sun.net.www.content.audio.x_aiff;
import tools.sendsms;
import tools.util.StringUtil;

import com.friendfinder.exception.ExtensionException;
import com.friendfinder.service.UserService;
import com.friendfinder.util.CycleImageUtil;
import com.friendfinder.util.InfoRateUtil;
import com.friendfinder.util.SplitPageUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 获取所有用户列表
	 * 
	 * @return
	 */
	@RequestMapping("/getAllUser.action")
	public @ResponseBody List<User> getAllUser() {
		List<User> findAll = userService.findAll();
		return findAll;
	}

	/**
	 * 注册添加新用户
	 * 
	 * @param req
	 *            请求流
	 * @param resp
	 *            响应流
	 * @param phone
	 *            手机号
	 * @param password
	 *            密码
	 * @param cycle
	 *            图片验证码
	 * @param password_again
	 *            重复密码
	 * @param sms_cycle
	 *            短信验证码
	 * @param  ' reg'
	 *            注册标志
	 * @return
	 * @throws ExtensionException
	 */
	@Transactional
	@RequestMapping("/addUser.action")
	public String addUser(
			HttpServletRequest req,
			HttpServletResponse resp,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "cycle", required = true) String cycle,
			@RequestParam(value = "password_again", required = true) String password_again,
			@RequestParam(value = "sms_cycle", required = true) String sms_cycle)
			throws ExtensionException {

		// 获取服务器保存的图片验证码和短信验证码
		String cycleCode = (String) req.getSession(true).getAttribute(
				"cycleCode");
		String smsCycle = (String) req.getSession(true).getAttribute(
				"sms_cycle");

		String reg = req.getParameter("reg");
		if (reg == null) {
			reg = "off";
			// 跳转页面
			throw new ExtensionException("未选中注册条例，注册失败!");
		}

		if (!password.equals(password_again)) {
			throw new ExtensionException("二次密码校验失败，无法注册!");
		}

		boolean add = false;
		if ("on".equals(reg)) {
			if (cycleCode.toUpperCase().equals(cycle.toUpperCase())
					&& smsCycle.equals(sms_cycle)) {
				add = userService.add(Long.parseLong(phone), StringUtil.MD5Encode(password));
				// 开启用户财富系统
				User s = userService.selectByPhone(Long.parseLong(phone));
				userService.insertAccount(s.getId());
				// 更新用户财富系统，默认初始积分和财富为10000
				UserAccount account = new UserAccount();
				account.setUserId(s.getId());
				account.setCharm(0);
				account.setPoint(10000);
				account.setWealth(10000);
				userService.updateAccount(account);
			} else {
				throw new ExtensionException("验证码验证失败!");
			}
		}
		if (add) {
			System.out.println("注册成功");
		}
		return "redirect:/html/login.jsp";
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user 用户
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateUser.action")
	public @ResponseBody Map<String, Object> updateUser(HttpServletRequest req, @RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer id = (Integer)req.getSession(true).getAttribute("id");
		if (id != null && user != null && id.equals(user.getId())) {
//			if (user.getHeight() == null) {
//				user.setHeight(0D);
//			}
//			if (user.getSalary() == null) {
//				user.setSalary(0D);
//			}
			
			boolean flag = userService.update(user);
			if (flag) {
				map.put("message", "修改用户信息成功!");
			} else {
				map.put("message", "1.修改用户信息失败!");
			}
		} else {
			map.put("message", "2.修改用户信息失败!");
		}
		return map;
	}
	
	/**
	 * 计算用户信息完善度(用于进度条显示)
	 * 
	 * @param u 用户信息
	 * @return
	 */
	private Integer calSum(User u){
		Integer integer = 0;	
		try {
			integer = InfoRateUtil.mainMethod(User.class, u);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return integer;
	}

	/**
	 * 根据id查询单个用户
	 */
	@RequestMapping("/findById.action")
	@ResponseBody
	public UserExtension findById(@RequestParam Integer id) {
		UserExtension ex = new UserExtension();
		User u = userService.findById(id);
		u.setPassword(null);
		ex.setAddress(u.getAddress());
		ex.setId(u.getId());
		ex.setBirthday(u.getBirthday());
		ex.setHeight(u.getHeight());
		ex.setIntroduce(u.getIntroduce());
		ex.setJob(u.getJob());
		ex.setMarriage(u.getMarriage());
		ex.setNickname(u.getNickname());
		ex.setPhone(u.getPhone());
		ex.setPortraitId(u.getPortraitId());
		ex.setSalary(u.getSalary());
		ex.setSex(u.getSex());
		
		Integer sum = calSum(u);
		ex.setSum(sum);
		
		return ex;
	}

	/**
	 * 删除用户
	 */
	@Transactional
	@RequestMapping("/delete.action")
	public void delete(@RequestParam("userId")Integer id) {
		System.out.println("删除" + id + "用户成功");
		userService.delete(id);
	}

	/**
	 * 注册用户是否已经存在
	 */
	@RequestMapping("/isExict.action")
	public void isExict(@RequestParam Long phone) {
		userService.isExict(phone);
	}

	/**
	 * 登录验证跳转
	 * 
	 * @param req
	 *            请求流
	 * @param resp
	 *            响应流
	 * @param phone
	 *            手机号
	 * @param password
	 *            密码
	 * @param cycle
	 *            图片验证码
	 * @param r em
	 *            记住密码选项
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @throws ExtensionException
	 */
	@RequestMapping("/isValid.action")
	public String isValid(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam(value = "username", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "cycle", required = true) String cycle)
			throws IOException, ServletException, ExtensionException {
		
		String rem = req.getParameter("remb_password");
		if (rem == null) {
			rem = "off";
		}
		User user = null;
		// 获取session中保存的验证码
		Boolean flag = false;
		System.err.println(password);
		String attr = (String) req.getSession(true).getAttribute("cycleCode");
		if (attr.toUpperCase().equals(cycle.toUpperCase())) { // 验证码比较:忽略大小写
			// 下面的操作作用:接收用户客户端传递的Cookie信息
			Cookie[] cookies = req.getCookies();// 获取客户端Cookie信息
			if (cookies != null) {// 假设客户端保存有用户的Cookie信息
				for (Cookie c : cookies) {
					if ("password".equals(c.getName()) && c.getValue().equals(password)) {
						flag = true;		
					}
				}
			}
			// 默认将用户密码进行MD5加密
			// 将获取用户密码进行加密，仅限没有设置记住密码的用户
			if (!flag) {
				password = StringUtil.MD5Encode(password);
			}
			
			System.out.println(password);

			// 进行数据库验证
			user = userService.isValid(Long.parseLong(phone), password);
			
			if (user == null) {
				throw new ExtensionException("用户名或密码错误!");
			}

			//userId放入cookie
			Cookie userId = new Cookie("userId", user.getId().toString());
			userId.setPath("/");
			userId.setMaxAge(3600 * 3);//默认时间3小时
			resp.addCookie(userId);

			if (user != null) { // 查询用户成功
				
				if (SessionListener.isLoginUser(user.getId().toString())) {
					System.out.println("后台显示用户" + user.getId() + "已经登录!");
				}
				
				// 保存用户session信息
				req.getSession(true).setAttribute("id", user.getId());
				req.getSession(true).setAttribute("nickName",
						user.getNickname());// 保存用户名信息
				if (!("off".equals(rem))) {// 判断用户是否选中'记住密码'选项
					// 保存用户Cookie信息，只能保存最近一次登录的Cookie信息
					Cookie name = new Cookie("username", phone);
					name.setPath("/"); // 设置cookie信息保存路径，此为站内共享路径
					Cookie pass = new Cookie("password", password);// 在Cookie中存入MD5加密密码
					pass.setPath("/"); // 设置cookie信息保存路径
					name.setMaxAge(3600 * 24 * 7);// 单位是秒,默认保存7天
					pass.setMaxAge(3600 * 24 * 7);
					resp.addCookie(name);
					resp.addCookie(pass);
				}
			}
		} else {
			throw new ExtensionException("验证码验证失败!");
		}
		req.setAttribute("user", user);
		return "redirect:/html/personInfo.jsp";
	}

	@RequestMapping("/cycleImg.action")
	@ResponseBody
	public String cycleImg(HttpServletRequest request) {
		CycleImageUtil util = new CycleImageUtil();
		// 画验证码
		util.drawImage();
		// 将生成结果存储在session中
		String result = util.getResult();
		request.getSession(true).setAttribute("cycleCode", result);
		// 返回Base64
		BufferedImage image = util.getImage();
		String img = util.encodeImage(image);
		return img;
	}

	@RequestMapping("/mobileCycle.action")
	@ResponseBody
	public void mobileCycle(HttpServletRequest request,
			@RequestParam("phone") String phone) throws ExtensionException {
		if (phone.matches("^1[3|7|4|5|8][0-9]{9}$")) {
			tools.sendsms.send(phone);
			request.getSession(true).setAttribute("sms_cycle",
					sendsms.getResult());
		} else {
			throw new ExtensionException("手机号不符合规范，注册失败!");
		}
	}

	// 按条件查找
	@RequestMapping("findCondition.action")
	public List<User> findCondition(@RequestParam Date createTime,
			@RequestParam Double salary, @RequestParam Integer age) {
		List<User> findCondition = userService.findCondition(createTime,
				salary, age);
		return findCondition;
	}
	
	/**
	 * 按条件查找指定人员信息
	 * 
	 * @param req 请求流
	 * @param pageNum 页码
	 * @param attr1 条件一
	 * @param attr2 条件二
	 * @param attr3 条件三
	 * @param attr4 条件四 
	 * @return
	 * @throws ExtensionException
	 */
	@RequestMapping("/test.action")
	@ResponseBody
	public List<UserExtension> test(HttpServletRequest req,
			@RequestParam("page") String pageNum,
			@RequestParam("attr1") Integer attr1,
			@RequestParam("attr2") Integer attr2,
			@RequestParam("attr3") Integer attr3,
			@RequestParam("attr4") Integer attr4) throws ExtensionException {
		List<UserExtension> list = new ArrayList<UserExtension>();
		
			// 创建查询对象
			UserQueryVo vo = new UserQueryVo();
			SplitPageUtil page = new SplitPageUtil();
			// 设置分页参数
			page.setPageSize(12);
			page.setPagePos(Integer.parseInt(pageNum));
			vo.setPage(page);
			// 生成并设置查询参数
			UserExtension e = new UserExtension();
			e.setAttr1(attr1);
			e.setAttr2(attr2);
			e.setAttr3(attr3);
			e.setAttr4(attr4);
			vo.setUser(e);
			
			// 获取登录用户id值
			Integer id = (Integer)req.getSession(true).getAttribute("id");
			// 获取该用户所关注的其他用户
			List<UserShip> list2 = userService.selectAllfans(null, id);
			
			// 设置关注者的标志
			list = userService.listOrderUser(vo);
			if (list != null) {
				// 指定条件下所有的用户
				for (UserExtension s : list) {
					// 用户自身所关注的用户
					for (UserShip u : list2) {
						// 判断两者是否相等
						if (u.getOwner().equals(s.getId())) {
							s.setFlag(1);
						} 
					}
					// 重新赋值为0
					if (s.getFlag() == null) {
						s.setFlag(0);
					}
				}	
			}
		return list;
	}
	
	/**
	 * 分页总页数
	 * 
	 * @param req 请求流
	 * @param attr1 参数一
	 * @param attr2 参数二
	 * @param attr3 参数三
	 * @param attr4 参数四
	 * @return
	 * @throws ExtensionException
	 */
	@RequestMapping("/test1.action")
	@ResponseBody
	public Integer test1(HttpServletRequest req,
			@RequestParam("attr1") Integer attr1,
			@RequestParam("attr2") Integer attr2,
			@RequestParam("attr3") Integer attr3,
			@RequestParam("attr4") Integer attr4) throws ExtensionException {
		
		// 创建查询对象
		UserQueryVo vo = new UserQueryVo();
		// 生成并设置查询参数
		UserExtension e = new UserExtension();
		e.setAttr1(attr1);
		e.setAttr2(attr2);
		e.setAttr3(attr3);
		e.setAttr4(attr4);
		vo.setUser(e);
		
		// 查询指定条件下的总人数
		int count = userService.countOrderUser(vo);
	
		// 返回分页的个数
		return (int)(Math.ceil(Double.valueOf(count) / Double.valueOf(12)));
	}

	/**************************好友系统*****************************/

	/**
	 * 功能 : 关注用户按钮的实现
	 * 地址 : localhost:8080/friendfinder/user/addFans?id=1
	 * 
	 * @param req 请求流
	 * @param fansId 被关注人的id
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/addFans.action", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> addFans(HttpServletRequest req, @RequestParam("id")Integer fansId) {
		// 返回Map集合
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 从session中获取当前用户的id
		Integer id = (Integer)req.getSession(true).getAttribute("id");
		// 由于本方法是添加粉丝，此时需要的是关注用户(注意逻辑别错了)
		if (id != null) {
			// 下面的逻辑别错了
			userService.insertfans(fansId, id);
			map.put("success", "关注成功!");
		} else {
			map.put("error", "关注失败!");
		}
		return map;
	}

	/**
	 * 功能 : 进行取消关注的实现
	 * 地址 : localhost:8080/friendfinder/user/delFans?id=1
	 * 
	 * @param req 请求流
	 * @param fansId 被取消关注的id
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/delFans.action", method = {RequestMethod.POST} )
	public @ResponseBody Map<String, Object> delFans(HttpServletRequest req, @RequestParam("id")Integer fansId) {
		// 返回Map集合
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 从session中获取当前用户的id
		Integer id = (Integer)req.getSession(true).getAttribute("id");
		if (id != null) {
			userService.delectfans(fansId, id);
			map.put("success", "取消关注成功!");
		} else {
			map.put("error", "取消关注失败!");
		}
		return map;
	}

	/**
	 * localhost:8080/friendfinder/user/selAllFans?userId=1
	 * 
	 * @param 'userId'
	 * @return nickname: id {"dyx2":3,"i":2}
	 */
	@RequestMapping(value = "/selAllFans.action", method = RequestMethod.GET)
	public List<UserShip> selAllFans(Integer owner ,Integer fans) {
		return userService.selectAllfans(owner,fans);
	}

	/**
	 * localhost:8080/friendfinder/user/selFans?userId=1&fansId=3
	 * 
	 * @param userId
	 * @param fansId
	 * @return true
	 */
//	@RequestMapping(value = "/selFans", method = RequestMethod.GET)
//	public boolean selFans(Integer userId, Integer fansId) {
//		if (userService.selectFans(userId, fansId) > 0)
//			return true;
//		else
//			return false;
//	}



	@RequestMapping(value = "/selFans.action",method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<UserSynopsis> selFansFllow(Integer userId){
		List<UserSynopsis> list = new ArrayList<UserSynopsis>();

		Integer[] userFansId = userService.selectFans(userId);
		int userFansLength = userFansId.length;
		UserSynopsis[] fansList = new UserSynopsis[userFansLength];

		for (int j = 0;j<userFansLength;j++){
			User user = userService.findById(userFansId[j]);
			if (user != null) {
				user.setPassword(null);
				fansList[j] = new UserSynopsis();
				fansList[j].setId(user.getId());
				fansList[j].setNickname(user.getNickname());
				fansList[j].setImgPath("http://139.199.16.239:8080/demo/image/"+attachmentService.showAttachment(user.getPortraitId()).getFileName());
				fansList[j].setIntroduce(user.getIntroduce());
				
				list.add(fansList[j]);
			}
					}
		return list;
	}
	@RequestMapping(value = "/selFllow.action",method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<UserSynopsis> selFllow(Integer userId){
		List<UserSynopsis> list = new ArrayList<UserSynopsis>();
		
		Integer[] userFllowId = userService.selectFllow(userId);
		int userFllowLength = userFllowId.length;
		UserSynopsis[] fllowList = new UserSynopsis[userFllowLength];

		for(int j = 0;j<userFllowLength;j++){
			User user = userService.findById(userFllowId[j]);
			if (user != null) {
				user.setPassword(null);
				System.out.println(user.getPortraitId());
				fllowList[j] = new UserSynopsis();
				fllowList[j].setId(user.getId());
				fllowList[j].setNickname(user.getNickname());
				fllowList[j].setImgPath("http://139.199.16.239:8080/demo/image/"+attachmentService.showAttachment(user.getPortraitId()).getFileName());
				fllowList[j].setIntroduce(user.getIntroduce());
				list.add(fllowList[j]);
			}
		}

		return list;
	}





	// 财富系统

	/**
	 * localhost:8080/friendfinder/user/addAccount?userId=1
	 * 
	 * @param userId
	 */
	@Transactional
	@RequestMapping(value = "/addAccount.action", method = RequestMethod.POST)
	public void addAccount(Integer userId) {
		userService.insertAccount(userId);
	}

	/***
	 * localhost:8080/friendfinder/user/delAccount?userId=1
	 * 
	 * @param userId
	 */
	@Transactional
	@RequestMapping(value = "/delAccount.action", method = RequestMethod.POST)
	public void delAccount(Integer userId) {
		userService.deleteAccount(userId);
	}

	/***
	 * localhost:8080/friendfinder/user/updAccount
	 * 
	 * @param userAccount
	 *            { "id":"1", "charm":"123", "wealth":"123", "point":"1233",
	 *            "userId":"1" }
	 *
	 */
	@Transactional
	@RequestMapping(value = "/updAccount.action", method = RequestMethod.POST)
	public void updAccount(@RequestBody UserAccount userAccount) {
		System.out.println(userAccount);
		userService.updateAccount(userAccount);
	}

	/***
	 * localhost:8080/friendfinder/user/selAccount?userId=1
	 * 
	 * @param userId
	 * @return {"charm":0,"point":0,"wealth":0}
	 */
	@RequestMapping(value = "/selAccount.action", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody UserAccount selAccount(Integer userId) {
		return userService.selectAccount(userId);
	}

	// 礼物
	// ********!!!****只需要使用更新礼物就ok，如果没有数据会自动添加
	/***
	 * localhost:8080/friendfinder/user/addGoods?id=1&num=5&userId=1
	 * 
	 * @param id
	 * @param num
	 * @param userId
	 */
	@Transactional
	@RequestMapping(value = "/addGoods.action", method = RequestMethod.POST)
	public void addGoods(Integer id, Integer num, Integer userId) {
		userService.insertGood(id, num, userId);
	}

	/***
	 * localhost:8080/friendfinder/user/updGoods?id=1&value=5&userId=1
	 *
	 * @param id
	 * @param value
	 * @param userId
	 */
	@Transactional
	@RequestMapping(value = "/updGoods.action", method = RequestMethod.POST)
	public void updGoods(Integer id, Integer value, Integer userId) {
		userService.updateGood(id, value, userId);
	}

	/***
	 * localhost:8080/friendfinder/user/selGoodsOwn?userId=1
	 * 
	 * @param userId
	 * @return {
	 *         {"charm":1,"discount_point":1.0,"discount_rmb":1.0,"id":3,"name"
	 *         :"name3","price_point":1,"price_rmb":1,"sales":0,"type":0}:1,
	 *         {"charm"
	 *         :1,"discount_point":1.0,"discount_rmb":1.0,"id":2,"name":"name2"
	 *         ,"price_point":1,"price_rmb":1,"sales":0,"type":0}:2,
	 *         {"charm":1,"discount_point"
	 *         :1.0,"discount_rmb":1.0,"id":1,"name":"name1"
	 *         ,"price_point":1,"price_rmb":1,"sales":0,"type":0}:5 }
	 */
	@RequestMapping(value = "/selGoodsOwn.action", method = RequestMethod.GET)
	public Map<Goods, Integer> selGoodsOwn(Integer userId) {
		return userService.selectGoodsOwn(userId);
	}
	
	/**
	 * 查询登录用户的库存商品数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectOrderGoods.action", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<GoodsOwnExtension> selectOrderGoods(HttpServletRequest req){
		Integer id = (Integer)req.getSession(true).getAttribute("id");
		List<GoodsOwnExtension> list = new ArrayList<GoodsOwnExtension>();
		if (id != null) {
			Map<Goods, Integer> map = userService.selectGoodsOwn(id);
			// 将Map集合转换为List集合
			Integer[] s = userService.selectGoodsNum(id);
			
			// 由于不是联合查询，所以遍历数据时需要倒序遍历，千万要注意
			for (Entry<Goods, Integer> entry : map.entrySet()) {
				// 封装商品扩展对象
				GoodsOwnExtension ex = new GoodsOwnExtension();
				ex.setGoods(entry.getKey());
				// 封装商品数量对象
				GoodsOwn g = new GoodsOwn();
				g.setId(entry.getKey().getId());
				g.setNum(entry.getValue());
				g.setUserId(id);
				ex.setGoodsOwn(g);
				
				System.out.println(entry.getKey().getImg());
				Attachment a = attachmentService.showAttachment(Integer.parseInt(entry.getKey().getImg()));
				ex.setImgPath("http://139.199.16.239:8080/demo/image/" + a.getFileName());

				
				// 将商品在封装至列表
				list.add(ex);
			}
		} else {
			// 若session中不存在id值，则将返回值置空
			list = null;
			System.out.println("无法获取session中的值!");
		}
		
		return list;
	}
	
	/**
	 * 用户赠送礼物
	 * 
	 * @param req 请求流
	 * @param giftList 赠送的礼物列表
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/giveGift.action", method = {RequestMethod.GET, RequestMethod.POST }, consumes = "application/json")
	public @ResponseBody Map<String, Object> giveGift(
			HttpServletRequest req, 
			@RequestBody List<GoodsOwnExtension> giftList, 
			@RequestParam("targetId")Integer targetId){
		// 返回至前端的成功或错误信息
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 从session中获取用户的id值，若不存在则直接返回错误信息
		Integer userId = (Integer)req.getSession(true).getAttribute("id");
		System.out.println(userId);
		if (userId != null && targetId != null) {	
			int sum = 0;
			for (GoodsOwnExtension o : giftList) {
				int trueGoodsNum = 0;
				// 1.更新送礼物者的礼物数量
				Integer goodsId = o.getGoodsOwn().getId(); // 商品id
				Integer newGoodNum1 = o.getGoodsOwn().getNum(); // 消费商品个数
				Integer oldGoodNum1 = userService.selectGoodNum(goodsId, userId);
				if (newGoodNum1 < oldGoodNum1) { // 符合逻辑进行更新操作
					// 若存在更新数量(这里应该是-的操作)
					userService.updateGood(goodsId, oldGoodNum1 - newGoodNum1, userId);
					trueGoodsNum = newGoodNum1 ;
				} else { // 若超出赠送全部
					// 更新原系统出现bug
					userService.deleteGood(goodsId, userId);
					trueGoodsNum = oldGoodNum1;
				}
				
				// 2.更新被赠送礼物用户的礼物数量
				Integer oldGoodNum2 = userService.selectGoodNum(goodsId, targetId);
				if (oldGoodNum2 == null) {
					userService.updateGood(goodsId, newGoodNum1, targetId);
				} else {
					userService.updateGood(goodsId, oldGoodNum2 + newGoodNum1, targetId);
				}
				
				// 3.增加送礼物者的魅力值(需要计算返回值前端)
				sum += trueGoodsNum * o.getGoods().getCharm();
			}
	
			// 更新被赠送者用户魅力值，并将结果返回至前端	
			UserAccount account1 = userService.selectAccount(targetId);
//			System.out.println(account.getUserId().toString() + account.getCharm());
			if (account1 != null) {
				account1.setCharm(account1.getCharm() + sum);
				userService.updateAccount(account1); // 提交更新
				map.put("message", "赠送成功，用户" + targetId + "增加" +  sum + "魅力值!");
				
			} 	
		} else {
			System.err.print("登录已失效(获取用户id值失败)，请重新登录!");
			map.put("message", "无法获取用户登录信息，请重新登录!");
		}
		
		return map;
	}

	/**
	 * 找回密码逻辑一(接收用户传入手机号和短信验证码)
	 * 
	 * @param request
	 *            请求流
	 * @param phone
	 *            手机号
	 * @throws ExtensionException
	 */
	@Transactional
	@RequestMapping("/foundAction1.action")
	public ModelAndView foundAction1(HttpServletRequest req,
			@RequestParam("phone") Long phone,
			@RequestParam("sms_cycle") String sms_cycle)
			throws ExtensionException {
		ModelAndView mv = new ModelAndView();
		// 接收手机验证码
		String smsCycle = (String) req.getSession(true).getAttribute(
				"sms_cycle");
		// 判断手机号是否存在
		if (userService.isExict(phone)) {
			// 判断手机号验证码是否正确
			if (smsCycle.equals(sms_cycle)) {
				// 将用户手机号存入session中
				req.getSession(true).setAttribute("userPhone", phone);
				mv.setViewName("foundPassword2");
			} else {
				throw new ExtensionException("验证码验证失败!");
			}
		} else {
			throw new ExtensionException("手机号不存在!");
		}
		return mv;
	}

	/**
	 * 找回密码逻辑二(接收用户密码和二次密码)
	 * 
	 * @param password 密码
	 * @param password_again 二次密码
	 * @return
	 * @throws ExtensionException 
	 */
	@Transactional
	@RequestMapping("/foundAction2.action")
	public ModelAndView foundAction2(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "password_again", required = true) String password_again) throws ExtensionException {
		// 判断用户session是否存在
		if (req.getSession(true).getAttribute("userPhone") != null){
			User user = new User();
			ModelAndView mv = new ModelAndView();
			if (password.equals(password_again)) { // 双次密码验证正确
				// 按手机号查找指定用户
				user = userService.selectByPhone((Long)req.getSession(true).getAttribute("userPhone"));
				// 重置用户密码
				user.setPassword(StringUtil.MD5Encode(password));
				// 开始执行
				userService.update(user);
				// 更改用户保存的cookie信息
				Cookie pass = new Cookie("password", StringUtil.MD5Encode(password));// 在Cookie中存入MD5加密密码
				pass.setPath("/"); // 设置cookie信息保存路径
				pass.setMaxAge(3600 * 24 * 7);// 单位是秒,默认保存7天
				resp.addCookie(pass);
				mv.setViewName("foundPassword3");
			} else { // 双次密码验证失败
				mv.setViewName("foundPassword4");
			}
			return mv;
		} else {
			throw new ExtensionException("请按正常找回密码流程访问!");
		}
	}

	@Transactional
	@RequestMapping(value = "/giftBy.action",method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public Object giftBy(@RequestBody GiftBy giftList){
		JSONObject jsonObject = new JSONObject();
		Integer userId = giftList.userId;
		System.out.println(userId);
		Integer totalRMB = 0;
		Integer totalPoint = 0;
		GiftByType[] list = giftList.list;
		for (int i = 0; i<list.length;i++){
			Integer price;
			if(list[i].type == 0){
				price = goodsService.selectGoodsById(list[i].id).getPrice_rmb();
				totalRMB +=price*list[i].num;}
			else {
				price = goodsService.selectGoodsById(list[i].id).getPrice_rmb();
				totalPoint += price*list[i].num;}
		}
		//if (totalPoint == 0 && totalRMB == 0)return false;
		UserAccount userAccount = userService.selectAccount(userId);
		System.out.println(userAccount);
		userAccount.setUserId(userId);
		System.out.println(userAccount);
		if(totalPoint > userAccount.getPoint() || totalRMB > userAccount.getWealth()) {
			jsonObject.put("message","购买失败，账户余额不足！");
			jsonObject.put("status","error");
			return jsonObject;
		}
		userAccount.setPoint(userAccount.getPoint() - totalPoint);
		userAccount.setWealth(userAccount.getWealth() - totalRMB);
		userService.updateAccount(userAccount);
		for (int i = 0; i<list.length;i++){
			userService.updateGood(list[i].id,list[i].num,userId);
		}
		jsonObject.put("message","购买成功!");
		jsonObject.put("status","success");
		return jsonObject;
	}	
}
