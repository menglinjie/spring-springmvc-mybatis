package com.friendfinder.model;

import java.util.Map;


public class GoodsOwn {	
	private Integer id;
	private Integer num;
	private Integer userId;
	
	private Map<Goods, Integer> own;

	public GoodsOwn() {		
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

	public Map<Goods, Integer> getOwn() {
		return own;
	}

	public void setOwn(Map<Goods, Integer> own) {
		this.own = own;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}
