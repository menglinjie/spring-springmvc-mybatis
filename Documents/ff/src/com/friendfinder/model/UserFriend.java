
package com.friendfinder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class UserFriend implements Serializable {

	private int userId;//用户id
	//private int[] userFriends;//用户的朋友
	//private int[] userFriendsDegree;//用户的好友度
	private Map<Integer,Integer> userFriends =new HashMap<Integer,Integer>();
	public UserFriend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFriend(int userId, HashMap<Integer, Integer> userFriends) {
		super();
		this.userId = userId;
		this.userFriends = userFriends;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Map<Integer, Integer> getUserFriends() {
		return userFriends;
	}
	public void setUserFriends(Map<Integer, Integer> userFriends) {
		this.userFriends = userFriends;
	}
	@Override
	public String toString() {
		return "UserFriend [userId=" + userId + ", userFriends=" + userFriends
				+ "]";
	}
	
	
	
}
