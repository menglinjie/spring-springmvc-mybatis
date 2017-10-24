package com.friendfinder.dao;

import java.util.List;

import com.friendfinder.model.Gift;

public interface GiftDao {

		// 插入一个礼物(id,name,type,message,price-rmb,price-point,discount,sales)
		public int insertGift(Gift gift);

		// 删除一个礼物
		public int deleteGift(Gift gift);

		// 修改礼物信息
		public int updateGift(Gift gift);
		
		// 查询所有礼物信息
		public List<Gift> selectAllGifts();
		
		// 根据ID查询礼物信息
		public Gift selectGiftByGiftId(int giftId);
		
		// 根据用户id查询该用户所有礼物
		public List<Gift> selectAllGiftsUserOwns(int userid);
		
		// 根据用户id查询该用户所有赠送礼物信息
		public List<Gift> selectGiftsSendByUser(int userid);

		
}
