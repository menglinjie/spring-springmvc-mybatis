package com.friendfinder.model;


public class Goods {

	private Integer id;
	private Integer state;
	private String name;
	private Integer charm;
	private Integer type;
	private Integer price_rmb;
	private Integer price_point;
	private Double discount_rmb;
	private Double discount_point;
	private String message;
	private Integer sales;
	private String img;
	public Goods() {
		super();
	}
	
	public Goods(Integer id, Integer state, String name, Integer charm, Integer type, Integer price_rmb, Integer price_point, Double discount_rmb, Double discount_point, String message, Integer sales, String img) {
		this.id = id;
		this.state = state;
		this.name = name;
		this.charm = charm;
		this.type = type;
		this.price_rmb = price_rmb;
		this.price_point = price_point;
		this.discount_rmb = discount_rmb;
		this.discount_point = discount_point;
		this.message = message;
		this.sales = sales;
		this.img = img;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {

		return img;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"id=" + id +
				", state=" + state +
				", name='" + name + '\'' +
				", charm=" + charm +
				", type=" + type +
				", price_rmb=" + price_rmb +
				", price_point=" + price_point +
				", discount_rmb=" + discount_rmb +
				", discount_point=" + discount_point +
				", message='" + message + '\'' +
				", sales=" + sales +
				", img='" + img + '\'' +
				'}';
	}
}
