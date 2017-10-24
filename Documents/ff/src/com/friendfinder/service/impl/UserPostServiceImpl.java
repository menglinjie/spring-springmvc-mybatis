package com.friendfinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendfinder.dao.PostDao;
import com.friendfinder.dao.UserPostDao;
import com.friendfinder.dao.UserPostDao.OwnerArticleType;
import com.friendfinder.model.Post;
import com.friendfinder.model.UserPost;
import com.friendfinder.service.UserPostService;

/**
 * 文章转发服务类
 * 
 * @author lenovo
 *
 */
@Service("userPostService")
public class UserPostServiceImpl implements UserPostService {

	@Autowired
	private UserPostDao userPostDao;

	@Override
	public void forward(UserPost userPost) {

		userPostDao.insert(userPost);

	}

	/**
	 * 查看自己所有文本 1. 查看自己写的文章 2. 查看转发别人的文章 3. 查看所有属于自己的文章(包括自己写的和转发的) me, others,
	 * all
	 */
	@Override
	public List<Post> showPost(OwnerArticleType type, Integer userId) {

		return userPostDao.selectPost(type, userId);

	}

	@Override
	public List<Post> showAllPost() {

		List<Post> allPost = userPostDao.selectAllPost();
		return allPost;
	}

}
