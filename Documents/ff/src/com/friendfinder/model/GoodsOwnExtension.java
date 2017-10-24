package com.friendfinder.model;

public class GoodsOwnExtension {
	private Goods goods = new Goods();	
	private GoodsOwn goodsOwn = new GoodsOwn();
	private String imgPath = "";
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public GoodsOwn getGoodsOwn() {
		return goodsOwn;
	}
	public void setGoodsOwn(GoodsOwn goodsOwn) {
		this.goodsOwn = goodsOwn;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}
