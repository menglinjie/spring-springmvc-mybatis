package com.friendfinder.service;

import java.util.List;

import com.friendfinder.model.Post;

import com.friendfinder.model.Post.Type;

public interface PostService {

	/**
	 * 保存文章
	 * 
	 * @param post
	 */
	public void save(Post post);

	/**
	 * 修改文章 博客能被修改，评论不能
	 * 
	 * @param post
	 */
	public void modify(Post post);

	/**
	 * 删除文章
	 * 
	 * @param id
	 */
	public void remove(Integer postId);

	/**
	 * 查询单个文章 查看一次访问量加一
	 * 
	 * @param title
	 * @return
	 */
	public Post showOne(Integer postId);

	/**
	 * 统计文章或评论数量
	 * 
	 * @param type
	 * @return
	 */
	public Integer countpost(Type type, Integer parentId);

	/**
	 * 统计文章或评论访问量
	 * 
	 * @param title
	 * @return
	 */
	public Integer countView(Integer postId);

	/**
	 * 根据文章id统计转发量
	 * 
	 * @param postId
	 * @return
	 */
	public Integer countForward(Integer postId);

	/**
	 * 根据父类id查看评论
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Post> showComment(Integer parentId);
}
