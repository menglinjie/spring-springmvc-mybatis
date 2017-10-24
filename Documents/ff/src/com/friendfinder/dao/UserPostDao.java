package com.friendfinder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.friendfinder.model.Post;
import com.friendfinder.model.UserPost;

public interface UserPostDao {

	public enum OwnerArticleType {
		me, others, all
	}

	// 插入(转发一条文章)
	public void insert(UserPost userPost);

	/**
	 * 1. 查看自己写的文章 2. 查看转发别人的文章 3. 查看所有属于自己的文章(包括自己写的和转发的)
	 * 未被删除的
	 * @param userId
	 * @return
	 */
	public List<Post> selectPost(@Param("type") OwnerArticleType type, @Param("userId") Integer userId);

	/**
	 * 统计文章转发量
	 * 
	 * @return
	 */
	public int countForward(Integer postId);
	
	/**
	 * 查看所有博客（包括被状态删除的）
	 * @return
	 */
	public List<Post> selectAllPost();
}
