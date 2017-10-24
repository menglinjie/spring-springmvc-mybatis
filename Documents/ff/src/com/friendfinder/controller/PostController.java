package com.friendfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.friendfinder.dao.PostDao;
import com.friendfinder.model.Post;
import com.friendfinder.model.Post.Type;
import com.friendfinder.service.PostService;
import com.friendfinder.service.UserPostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private PostDao postDao;
	@Autowired
	private UserPostService userPostService;
	
	@RequestMapping(value = "/getPost.action", method = {RequestMethod.GET,RequestMethod.POST})
	public List<Post> getPost() {

		//List<AdminPost> adminPosts = new LinkedList<>();// 所有post集合
		List<Post> allPost = userPostService.showAllPost();// 所有post
		
		/*// 遍历AllPost
		for (Post p : allPost) {
			Integer countView = postDao.countView(p.getId());// 获取该post的访问量
			Integer countComment = postDao.countPost(Type.comment, p.getId());// 获取该post的评论量

			if (countComment==null) {
				
				countComment = 0;//加入为空值为0
			}
			AdminPost aPost = new AdminPost(p.getId(), p.getTitle(), countView, countComment, p.getWriteTime(),
					p.getType() + "", p.getState() + "");//将原post、评论量、阅读量封装
			
			adminPosts.add(aPost);//将封装后的Adminpost放进list
		}*/

		return allPost;
	}

	/**
	 * 保存 测试数据 http://localhost:8888/friend_finder/post/save { "content": "加油",
	 * "type": "comment", "user": { "id": 2 }, "post":{ "id":14 } }
	 * 
	 * @param post
	 */
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(@RequestBody Post post) {

		postService.save(post);
	}

	/**
	 * 修改博客信息 测试数据 { "id":4, "title": "mlj", "content": "11111", "brief": "11",
	 * "state": "on", "type": "article", "user": { "id": 2 } }
	 * 
	 * @param post
	 */

	@RequestMapping(value = "/modify.action", method = RequestMethod.POST)
	public void modify(@RequestBody Post post) {

		postService.modify(post);
	}

	/**
	 * 根据id删除文章
	 * 
	 * 测试数据 postId 11
	 * 
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/remove.action", method = RequestMethod.DELETE)
	public void remove(@RequestParam Integer postId) {

		postService.remove(postId);
	}

	/**
	 * 根据postId状态删除，删除后state为off 测试数据
	 * http://localhost:8888/friend_finder/post/setOff?postId=1
	 * 
	 * 
	 * @param postId
	 */
	@RequestMapping(value = "/setOff.action", method = {RequestMethod.GET, RequestMethod.POST})
	public void setOff(@RequestParam Integer postId) {

		postDao.updatePostOff(postId);
	}

	/**
	 * 根据文章id查看文章（文章，博客，用户） 测试数据
	 * http://localhost:8888/friend_finder/post/showOne?postId=4 { "brief":
	 * "11", "comments": [ { "brief": "11", "content": "11111", "id": 1,
	 * "state": "on", "title": "测试1", "type": "article", "user": { "address":
	 * "", "birthday": 1494248397985, "createTime": 1494248397985, "id": 1,
	 * "nickname": "dyx", "password": "123456", "phone": "1", "sex": false } }
	 * ], "content": "11111", "id": 4, "state": "on", "title": "mlj", "type":
	 * "article", "user": { "address": "", "birthday": 1494248397985,
	 * "createTime": 1494248397985, "id": 1, "nickname": "dyx", "password":
	 * "123456", "phone": "1", "sex": false } }
	 * 
	 * 
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "/showOne.action", method = RequestMethod.GET)
	public Post showOne(@RequestParam Integer postId) {

		return postService.showOne(postId);
	}

	/**
	 * 通过类型 统计文章或评论数量 测试数据
	 * http://localhost:8888/friend_finder/post/countPost?type=article 6
	 * 
	 * 
	 * @param type
	 *            parentId
	 * @return
	 */
	@RequestMapping(value = "/countPost.action", method = RequestMethod.GET)
	public Integer countpost(@RequestParam Type type, @RequestParam Integer parentId) {

		return postService.countpost(type, parentId);
	}

	/**
	 * 根据id统计文章访问量 测试数据
	 * http://localhost:8888/friend_finder/post/countView?postId=4 1
	 * 
	 * 
	 * @param postId
	 * @return
	 */
	@RequestMapping(value = "/countView.action", method = RequestMethod.GET)
	public Integer countView(@RequestParam Integer postId) {

		return postService.countView(postId);
	}

	/**
	 * 查看该博客所有评论
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/showComment.action", method = RequestMethod.GET)
	public List<Post> showComment(@RequestParam Integer parentId) {

		return postService.showComment(parentId);
	}
}
