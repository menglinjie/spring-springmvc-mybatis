package com.friendfinder.model;

public class GiftOwn {
	private int userId;//用户id
	private int giftId;//礼物id
	private int giftCount;//礼物数量
	public GiftOwn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GiftOwn(int userId, int giftId, int giftCount) {
		super();
		this.userId = userId;
		this.giftId = giftId;
		this.giftCount = giftCount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public int getGiftCount() {
		return giftCount;
	}
	public void setGiftCount(int giftCount) {
		this.giftCount = giftCount;
	}
	
}
