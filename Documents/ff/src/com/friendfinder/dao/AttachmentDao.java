package com.friendfinder.dao;

import com.friendfinder.model.Attachment;

import java.util.List;

public interface AttachmentDao {

	// 插入一个附件（上传）
	public int insertAttachment(Attachment attachment);

	// 根据id状态删除附件
	public void remove(Integer attachmentId);

	// 修改附件信息（图片名字）
	public int updateattachment(Attachment attachment);

	
	// 根据用户id查看单个附件
	public Attachment selectOne(Integer userId);
	
	//根据附件id查看单个附件
	public Attachment selectAttachment(Integer attachmentId);

	// 查询附件数量
	public int countattachment();
	
	// 图片下载


	//查询数组文件名
	public List<String> selectFileName(Integer[] id);
	// 查询最后一个
	public Attachment selectLastOne();
}
