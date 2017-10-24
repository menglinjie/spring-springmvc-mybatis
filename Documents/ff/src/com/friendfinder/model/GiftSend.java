package com.friendfinder.model;

import java.util.Date;

public class GiftSend {
	private int giftFrom;//赠礼物人
	private int giftTo;//受礼物人
	private int giftId;//礼物id
	private Date giftSendTime;//礼物赠送时间
	private String giftSendMessage;//礼物赠送信息
	public GiftSend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GiftSend(int giftFrom, int giftTo, int giftId, Date giftSendTime,
			String giftSendMessage) {
		super();
		this.giftFrom = giftFrom;
		this.giftTo = giftTo;
		this.giftId = giftId;
		this.giftSendTime = giftSendTime;
		this.giftSendMessage = giftSendMessage;
	}
	public int getGiftFrom() {
		return giftFrom;
	}
	public void setGiftFrom(int giftFrom) {
		this.giftFrom = giftFrom;
	}
	public int getGiftTo() {
		return giftTo;
	}
	public void setGiftTo(int giftTo) {
		this.giftTo = giftTo;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public Date getGiftSendTime() {
		return giftSendTime;
	}
	public void setGiftSendTime(Date giftSendTime) {
		this.giftSendTime = giftSendTime;
	}
	public String getGiftSendMessage() {
		return giftSendMessage;
	}
	public void setGiftSendMessage(String giftSendMessage) {
		this.giftSendMessage = giftSendMessage;
	}
	
}
