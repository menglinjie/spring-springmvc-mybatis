package com.friendfinder.model;
/**
 * @deprecated 商品信息类
 * 
 * @param id,name,charm,type,price_rmb,price_point,discount_rmb,discount_point,message,sales
 * 
 * */
@Deprecated
public class Good {

	private Integer id;
	private String name;
	private Integer charm;
	private Integer type;
	private Integer price_rmb;
	private Integer price_point;
	private Double discount_rmb;
	private Double discount_point;
	private String message;
	private Integer sales;
	public Good() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Good(String name, Integer charm, Integer type, Integer price_rmb,
			Integer price_point, String message) {
		super();
		this.name = name;
		this.charm = charm;
		this.type = type;
		this.price_rmb = price_rmb;
		this.price_point = price_point;
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCharm() {
		return charm;
	}
	public void setCharm(Integer charm) {
		this.charm = charm;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPrice_rmb() {
		return price_rmb;
	}
	public void setPrice_rmb(Integer price_rmb) {
		this.price_rmb = price_rmb;
	}
	public Integer getPrice_point() {
		return price_point;
	}
	public void setPrice_point(Integer price_point) {
		this.price_point = price_point;
	}
	public Double getDiscount_rmb() {
		return discount_rmb;
	}
	public void setDiscount_rmb(Double discount_rmb) {
		this.discount_rmb = discount_rmb;
	}
	public Double getDiscount_point() {
		return discount_point;
	}
	public void setDiscount_point(Double discount_point) {
		this.discount_point = discount_point;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	
}
