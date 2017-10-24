package com.friendfinder.model;

import java.util.ArrayList;
import java.util.*;
import com.friendfinder.util.SplitPageUtil;

/**
 * 查询对象封装(主要用于封装常用的查询对象)
 * 
 * @author leaf
 *
 */
public class UserQueryVo {
	// 扩展查询对象
	private UserExtension user = new UserExtension();
	// 分页查询对象
	private SplitPageUtil page = new SplitPageUtil();
	// 查询结果列表
	private List<UserExtension> list = new ArrayList<UserExtension>();
	// Map集合(暂无用)
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public UserQueryVo() {
		super();
	}
	public SplitPageUtil getPage() {
		return page;
	}
	public void setPage(SplitPageUtil page) {
		this.page = page;
	}
	public List<UserExtension> getList() {
		return list;
	}
	public void setList(List<UserExtension> list) {
		this.list = list;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public UserExtension getUser() {
		return user;
	}
	public void setUser(UserExtension user) {
		this.user = user;
	}	
}
