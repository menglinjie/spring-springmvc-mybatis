package com.friendfinder.service;

import java.util.List;

import com.friendfinder.dao.UserPostDao.OwnerArticleType;
import com.friendfinder.model.Post;
import com.friendfinder.model.UserPost;

public interface UserPostService {

	/**
	 * 转发文章
	 * 
	 * @param userPost
	 */
	public void forward(UserPost userPost);

	/**
	 * 查看转发内容 1. 查看自己写的文章 2. 查看转发别人的文章 3. 查看所有属于自己的文章(包括自己写的和转发的)
	 * 
	 * @return
	 */
	public List<Post> showPost(OwnerArticleType type, Integer userId);
	
	/**
	 * 查看所有博客，包括被删除的
	 * @return
	 */
	public List<Post> showAllPost();
	
}
