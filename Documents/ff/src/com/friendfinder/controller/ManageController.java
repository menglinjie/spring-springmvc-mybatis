package com.friendfinder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.friendfinder.exception.ExtensionException;
import com.friendfinder.model.Account;
import com.friendfinder.model.Attachment;
import com.friendfinder.model.Goods;
import com.friendfinder.model.Manage;
import com.friendfinder.model.User;
import com.friendfinder.service.AttachmentService;
import com.friendfinder.service.GoodsService;
import com.friendfinder.service.ManageService;
import com.friendfinder.service.UserService;
import com.friendfinder.util.UploadFileUtil;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@Autowired
	private ManageService manageService;
	@Autowired
	private UserService userService;
	@Autowired
    private GoodsService goodsService;
	@Autowired
	private AttachmentService attachmentService;
	
	/**
	 * 登录校验
	 * 
	 * @param req
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/isLogin.action", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView isLogin(HttpServletRequest req, @RequestParam("username")String name, @RequestParam("password")String password){
		Manage m = manageService.selectManage(name, password);
		ModelAndView mv = new ModelAndView();
		if (m != null) {
			req.getSession(true).setAttribute("admin", "admin");
			mv.setViewName("backgroundInfo");
		} else {
			mv.setViewName("error");
			mv.addObject("message", "权限校验失败，请重新登录");
		}
		
		return mv;
	}

	/**
	 * 查询所有用户信息
	 * @return
	 */
	@RequestMapping(value="/selectAllUser.action", method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<Account> selectAllUser(){
		List<Account> list = manageService.selectAllUser();
//		for (Account i : list) {
//			System.err.println(i.getState() + "--" + i.getCreateTime());
//		}
		return list;
	}
	
	/**
	 * 查询所有商品信息
	 * 
	 * @return
	 */
	@RequestMapping(value="/selectAllGoods.action", method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<Goods> selectAllGoods(){
		List<Goods> list = manageService.selectAllGoods();
		
		for (Goods g : list) {
			System.out.println(g.getDiscount_point() + "--" + g.getDiscount_rmb());
		}
		return list;
	}
	
	
	/**
	 * 查询所有商品信息(针对管理员)
	 * 
	 * @return
	 */
	@RequestMapping(value="/selectAllGoodsForManage.action", method={RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<Goods> selectAllGoodsForManage(){
		List<Goods> list = manageService.selectAllGoodsForManage();
		return list;
	}
	
	/**
	 * 恢复用户
	 * 
	 * @param req
	 * @param id
	 * @throws ExtensionException
	 */
	@Transactional
	@RequestMapping(value="/backUser.action", method={RequestMethod.POST, RequestMethod.GET})
	public void backUser(HttpServletRequest req, @RequestParam("userId")Integer id) throws ExtensionException{
		User user = new User();
		user.setId(id);
		user.setState(0);
		userService.update(user);
	}
	
	/**
	 * 恢复博客文章
	 * 
	 * @param req
	 * @param id
	 * @throws ExtensionException
	 */
	@Transactional
	@RequestMapping(value="/backBlog.action", method={RequestMethod.POST, RequestMethod.GET})
	public void backBlog(HttpServletRequest req, @RequestParam("postId")Integer id) throws ExtensionException{
		manageService.updateBlog("on", id);
	}
	
	/**
	 * 更新商品状态
	 * 
	 * @param req
	 * @param id
	 * @throws ExtensionException
	 */
	@Transactional
	@RequestMapping(value="/backGoods.action", method={RequestMethod.POST, RequestMethod.GET})
	public void backGoods(HttpServletRequest req, @RequestParam("id")Integer id) throws ExtensionException{
		Goods goods = new Goods();
		goods.setId(id);
		goods.setState(1);
		manageService.updateGoods(goods);
	}
	
	/**
	 * 更新商品信息
	 * 
	 * @param req
	 * @param id
	 * @throws ExtensionException
	 * @throws IOException 
	 */
	@Transactional
	@RequestMapping(value="/updateGoodsInfo.action", method={RequestMethod.POST, RequestMethod.GET})
	public String updataGoodsInfo(HttpServletResponse resp, Goods goods, MultipartFile imgFile) throws ExtensionException, IOException{	
		// 上传商品图片
		System.out.println(imgFile.getSize());
		System.out.println(imgFile.getName());
		System.out.println(imgFile.getOriginalFilename());
	
		
		if (goods != null) {
			if (imgFile != null && imgFile.getSize() != 0) {
				UploadFileUtil uploadFileUtil = new UploadFileUtil();
				if (uploadFileUtil.upload(imgFile)) {
					String nativeName = imgFile.getOriginalFilename();
					String type = imgFile.getContentType();
					String fileName = uploadFileUtil.getNewFileName();
					// 封装对象并上传图片
					Attachment attachment = new Attachment();
					attachment.setNativeName(nativeName);
					attachment.setFileName(fileName);
					attachment.setFileType(type);
					System.out.println(fileName);
					Attachment a = attachmentService.saveAttachment(attachment);
					
					goods.setImg(String.valueOf(a.getId()));
				}
			}
			// 更新的商品信息
			manageService.updateGoods(goods);
			
			System.out.println("插入商品成功!");
			return "backgroundInfo";
		} else {
			throw new ExtensionException("商品添加失败!");
		}
	}
	
	/**
	 * 插入商品信息
	 * 
	 * @param req
	 * @param goods
	 * @param img
	 * @throws ExtensionException
	 * @throws IOException 
	 */
	@Transactional
	@RequestMapping(value="/insertGoodsInfo.action", method={RequestMethod.POST, RequestMethod.GET})
	public String insertGoodsInfo(HttpServletResponse resp, Goods goods, MultipartFile imgFile) throws ExtensionException, IOException{
		// 上传商品图片
		System.out.println(imgFile.getSize());
		System.out.println(imgFile.getName());
		System.out.println(imgFile.getOriginalFilename());
		
		if (goods != null) {
			if (imgFile != null && imgFile.getSize() != 0) {
				UploadFileUtil uploadFileUtil = new UploadFileUtil();
				if (uploadFileUtil.upload(imgFile)) {
					String nativeName = imgFile.getOriginalFilename();
					String type = imgFile.getContentType();
					String fileName = uploadFileUtil.getNewFileName();
					// 封装对象并上传图片
					Attachment attachment = new Attachment();
					attachment.setNativeName(nativeName);
					attachment.setFileName(fileName);
					attachment.setFileType(type);
					System.out.println(fileName);
					Attachment a = attachmentService.saveAttachment(attachment);
					
					goods.setImg(String.valueOf(a.getId()));
				}
			}
			// 插入新的商品信息
			goodsService.addGoods(goods);
			
			System.out.println("插入商品成功!");
			
			return "backgroundInfo";
			
		} else {
			throw new ExtensionException("商品添加失败!");
		}
	}
	
	 
	@RequestMapping(value="/goodsTransfer.action", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView goodsTransfer(HttpServletRequest req) throws ExtensionException{
		// 设置跳转视图
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addGoods");
		
		// 接收用户传递参数
		String para = req.getParameter("id");
			
		// 封装参数值
		if (para != null && !"".equals(para)) {
			Integer id = Integer.parseInt(para);
			Goods g = goodsService.selectGoodsById(id);
			g.setImg(attachmentService.showAttachment(Integer.parseInt(g.getImg())).getFileName());
			mv.addObject("goods", g);
			mv.addObject("state", "修改商品信息");
			mv.addObject("url", "updateGoodsInfo.action");
		} else {
			mv.addObject("state", "添加商品信息");
			mv.addObject("url", "insertGoodsInfo.action");
		}
		
		return mv;
	}
		
}	
