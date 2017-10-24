package com.friendfinder.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AdminPost {

	private Integer id;
	private String title;
	private Integer countView;
	private Integer countComment;
	
	@JSONField(format="yyyy-MM-dd")//时间戳类型转换为字符串类型
	private Date time;
	private String type;
	private String state;

	public AdminPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminPost(Integer id, String title, Integer countView, Integer countComment, Date time, String type,
			String state) {
		super();
		this.id = id;
		this.title = title;
		this.countView = countView;
		this.countComment = countComment;
		this.time = time;
		this.type = type;
		this.state = state;
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

	public Integer getCountView() {
		return countView;
	}

	public void setCountView(Integer countView) {
		this.countView = countView;
	}

	public Integer getCountComment() {
		return countComment;
	}

	public void setCountComment(Integer countComment) {
		this.countComment = countComment;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
