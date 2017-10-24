package com.friendfinder.model;

/**
 * "关注"与"粉丝"实体类
 * 
 * @author leaf
 *
 */
public class UserShip {
	private Integer id;
	private Integer owner;
	private Integer fans;
	public Integer getFans() {
		return fans;
	}
	public void setFans(Integer fans) {
		this.fans = fans;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
