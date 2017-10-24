package com.friendfinder.model;

import java.util.List;
/**
 * 
 * @deprecated 用户朋友类
 * 
 * @param fansId	粉丝ID集合,只读
 * @param fans		粉丝信息集合，只读
 * */
@Deprecated
public class Friends {
	private Integer id;
	private Integer userId;
	private List<Integer> fansId;
	
	private List<User> fans;

	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getFansId() {
		return fansId;
	}

	public void setFansId(List<Integer> fansId) {
		this.fansId = fansId;
	}

	public List<User> getFans() {
		return fans;
	}

	public void setFans(List<User> fans) {
		this.fans = fans;
	}

	

}
