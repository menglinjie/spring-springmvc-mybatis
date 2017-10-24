package com.friendfinder.model;

import java.io.Serializable;
/**
 * 文章类
 */
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Post implements Serializable {

	private Integer id;// 文章id
	private String title;// 标题
	private String content;// 内容
	private String brief;// 文章摘要
	private Integer countView;// 访问量
	private Integer countForward;// 转发量

	private enum State {
		on, off
	}

	private State state;// 文章状态（删除，未删除）

	@JSONField(format = "yyyy-MM-dd hh:mm:ss") // 时间戳类型转换为字符串类型
	private Date writeTime;// 创建时间

	public enum Type {
		article, comment
	}

	private Type type;// 文本类型(文章，评论)
	private User user;// 创建人
	private List<Post> comments;// 一个文章对应多个评论;
	private Post article;// 一个评论对应一个父类文章
	private Integer creatorId;

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getCountView() {
		return countView;
	}

	public void setCountView(Integer countView) {
		this.countView = countView;
	}

	public Integer getCountForward() {
		return countForward;
	}

	public void setCountForward(Integer countForward) {
		this.countForward = countForward;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getComments() {
		return comments;
	}

	public void setComments(List<Post> comments) {
		this.comments = comments;
	}

	public Post getArticle() {
		return article;
	}

	public void setArticle(Post article) {
		this.article = article;
	}

}
