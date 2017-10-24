package com.friendfinder.model;

public class UserAccount {

	private Integer id;
	private Integer charm;//魅力值
	private Integer wealth;//财富值
	private Integer point;//积分
	private Integer userId;
	
	private GoodsOwn goods;//商品拥有数量
	public UserAccount() {
		super();
	}
	public Integer getCharm() {
		return charm;
	}
	public void setCharm(Integer charm) {
		this.charm = charm;
	}
	public Integer getWealth() {
		return wealth;
	}
	public void setWealth(Integer wealth) {
		this.wealth = wealth;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public GoodsOwn getOwn() {
		return goods;
	}
	public void setOwn(GoodsOwn goods) {
		this.goods = goods;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
}
