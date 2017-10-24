package com.friendfinder.model;

import java.sql.Timestamp;

/**
 * User类扩展对象
 * 
 * @author leaf
 *
 */
public class UserExtension extends User {
	private static final long serialVersionUID = -1400529837858077160L;

	// 用户是否被关注标志
	// 0代表未关注，1代表已关注
	private Integer flag;
	
	// 魅力值信息
	private Long charm;
	
	// 头像图片名字
	private String picture;
	
	// 计算用户信息完善度
	private Integer sum;
	
	// 查询条件1(全部, 魅力值排行, 最新注册)
	private Integer attr1;
	
	// 查询条件2(全部, 男, 女)
	private Integer attr2;
	
	// 查询条件3(全部, 18-28岁, 28-38岁, 38-48岁, 48以上)
	private Integer attr3;
	
	// 查询条件4(全部, 3000元以下, 3000-5000元, 5000-7000元, 7000-9000元, 9000元以上)
	private Integer attr4;

	public UserExtension() {
		super();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getAttr1() {
		return attr1;
	}

	public void setAttr1(Integer attr1) {
		this.attr1 = attr1;
	}

	public Integer getAttr2() {
		return attr2;
	}

	public void setAttr2(Integer attr2) {
		this.attr2 = attr2;
	}

	public Integer getAttr3() {
		return attr3;
	}

	public void setAttr3(Integer attr3) {
		this.attr3 = attr3;
	}

	public Integer getAttr4() {
		return attr4;
	}

	public void setAttr4(Integer attr4) {
		this.attr4 = attr4;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getCharm() {
		return charm;
	}

	public void setCharm(Long charm) {
		this.charm = charm;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}
}
