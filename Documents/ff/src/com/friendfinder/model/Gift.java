package com.friendfinder.model;

public class Gift {
	private int giftId;//礼物id
	private String giftName;//礼物名称
	private String giftType;//礼物类型
	private String giftMessage;//礼物信息
	private Double giftPriceRMB;//礼物价格-rmb
	private Double giftPricePoint;//礼物价格-积分
	private Double giftDiscount;//礼物折扣
	private int giftSales;//礼物销量
	
	public Gift() {		
	}
	public Gift(int giftId, String giftName, String giftType,
			String giftMessage, Double giftPriceRMB, Double giftPricePoint,
			Double giftDiscount, int giftSales) {
		
		this.giftId = giftId;
		this.giftName = giftName;
		this.giftType = giftType;
		this.giftMessage = giftMessage;
		this.giftPriceRMB = giftPriceRMB;
		this.giftPricePoint = giftPricePoint;
		this.giftDiscount = giftDiscount;
		this.giftSales = giftSales;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public String getGiftType() {
		return giftType;
	}
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	public String getGiftMessage() {
		return giftMessage;
	}
	public void setGiftMessage(String giftMessage) {
		this.giftMessage = giftMessage;
	}
	public Double getGiftPriceRMB() {
		return giftPriceRMB;
	}
	public void setGiftPriceRMB(Double giftPriceRMB) {
		this.giftPriceRMB = giftPriceRMB;
	}
	public Double getGiftPricePoint() {
		return giftPricePoint;
	}
	public void setGiftPricePoint(Double giftPricePoint) {
		this.giftPricePoint = giftPricePoint;
	}
	public Double getGiftDiscount() {
		return giftDiscount;
	}
	public void setGiftDiscount(Double giftDiscount) {
		this.giftDiscount = giftDiscount;
	}
	public int getGiftSales() {
		return giftSales;
	}
	public void setGiftSales(int giftSales) {
		this.giftSales = giftSales;
	}
	
}
