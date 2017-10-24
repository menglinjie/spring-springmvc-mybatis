package com.friendfinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendfinder.dao.PostDao;
import com.friendfinder.dao.UserPostDao;
import com.friendfinder.model.Post;
import com.friendfinder.model.Post.Type;
import com.friendfinder.model.User;
import com.friendfinder.model.UserPost;
import com.friendfinder.service.PostService;

/**
 * 文章服务类
 * 
 * @author
 *
 */
@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	@Autowired
	private UserPostDao userPostDao;

	@Override
	public void save(Post post) {

		postDao.insertPost(post);

		UserPost userPost = new UserPost();
		userPost.setPost(post);
		userPost.setUser(post.getUser());

		userPostDao.insert(userPost);// 插入post同时关系表插入一条，方便查询
	}

	@Override
	public void modify(Post post) {

		postDao.updatepost(post);
	}

	@Override
	public void remove(Integer postId) {

		postDao.deletepost(postId);
	}

	@Override
	public Post showOne(Integer postId) {

		Post post = postDao.selectpostBypostId(postId);
		postDao.updateView(postId);// 查看一次，访问量加1
		return post;

	}

	@Override
	public Integer countpost(Type type, Integer parentId) {

		return postDao.countPost(type, parentId);
	}

	@Override
	public Integer countView(Integer postId) {

		return postDao.countView(postId);
	}

	@Override
	public Integer countForward(Integer postId) {

		return postDao.countForward(postId);
	}

	@Override
	public List<Post> showComment(Integer parentId) {

		List<Post> comments = postDao.selectComment(parentId);
		return comments;
	}

}
