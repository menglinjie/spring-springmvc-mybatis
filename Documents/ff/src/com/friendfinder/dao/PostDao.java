package com.friendfinder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.friendfinder.model.Post;
import com.friendfinder.model.Post.Type;

public interface PostDao {

	// 插入一个文本
	public void insertPost(Post post);

	// 根据post id删除一个文本
	public void deletepost(Integer postId);

	// 状态删除文章
	public void updatePostOff(Integer postId);

	// 修改文本信息
	public void updatepost(Post post);

	// 根据文章id查询单个文本，查看一次访问量加一
	public Post selectpostBypostId(Integer postId);

	// 根据文章id修改访问量+1
	public void updateView(Integer postId);

	// 根据文章id修改转发量+1
	public void updateForward(Integer postId);

	// 查询文章数量（type：文章，评论）parent_id(postId)
	public Integer countPost(@Param("type") Type type,@Param("parentId") Integer parentId);

	// 根据postID查看查询文章访问量
	public Integer countView(Integer postId);

	// 根据postid统计转发量
	public Integer countForward(Integer postId);

	//根据父类id查看评论(未被删除)
	public List<Post> selectComment(Integer parentId);
}
