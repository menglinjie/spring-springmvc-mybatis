package com.friendfinder.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class Account extends User {
	private Integer state;
	
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	private Integer charm;//魅力值
	private Integer wealth;//财富值
	private Integer point;//积分
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

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
