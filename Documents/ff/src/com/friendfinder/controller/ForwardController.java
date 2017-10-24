package com.friendfinder.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.friendfinder.dao.UserPostDao;
import com.friendfinder.dao.UserPostDao.OwnerArticleType;
import com.friendfinder.model.Post;
import com.friendfinder.model.Post.Type;
import com.friendfinder.model.UserPost;
import com.friendfinder.service.PostService;
import com.friendfinder.service.UserPostService;

/**
 * 转发控制层
 * 
 * @author lenovo
 *
 */
@RestController
@RequestMapping("/forward")
public class ForwardController {

	@Autowired
	private UserPostService userPostService;
	@Autowired
	private UserPostDao userpostDao;
	@Autowired
	private PostService postService;

	/**
	 * 转发文章 测试数据 http://localhost:8888/friend_finder/forward/forwardPost {
	 * "user":{ "id":1 }, "post":{ "id":8 } }
	 * 
	 * 
	 * 
	 * @param userPost
	 */
	@RequestMapping(value = "/forwardPost.action", method = RequestMethod.POST)
	public void forward(@RequestBody UserPost userPost) {

		userPostService.forward(userPost);
	}

	/**
	 * 查看所有转发文本 测试数据
	 * http://localhost:8888/friend_finder/forward/showAllForward?type=others&userId=2
	 *
	 *[
		  {
		    "brief": "11",
		    "content": "11111",
		    "id": 4,
		    "state": "off",
		    "title": "mlj",
		    "type": "article"
		  },
		  {
		    "brief": "44",
		    "content": "44444",
		    "id": 5,
		    "state": "on",
		    "title": "测试4",
		    "type": "article"
		  }
		]
	 *
	 *
	 * @return
	 */
	@RequestMapping("/showAllForward.action")
	public List<Post> showAllForward(@RequestParam(required = false) OwnerArticleType type,
			@RequestParam(required = true) Integer userId) {

		if (StringUtils.isBlank(type.toString())) {
			type = UserPostDao.OwnerArticleType.all;
		}
		// 循环遍历，查询文章评论数
		List<Post> list = userPostService.showPost(type, userId);
		for (Post p : list) {
			Integer i = postService.countpost(Type.comment, p.getId());
			p.setCountForward(i);
		}
		return list;
	}

	
	
	/**
	 * 统计转发量
	 *  测试数据 
	 *  http://localhost:8888/friend_finder/forward/countForward?postId=8
	 *  2
	 * @return
	 */
	@RequestMapping(value = "/countForward.action", method = RequestMethod.GET)
	public int countForward(Integer postId) {
		return userpostDao.countForward(postId);
	}
}
