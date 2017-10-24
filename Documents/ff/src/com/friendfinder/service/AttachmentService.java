package com.friendfinder.service;

import com.friendfinder.model.Attachment;

import java.util.List;

/**
 * 图片服务层
 * 
 * @author lenovo
 *
 */

public interface AttachmentService {

	/**
	 * 保存图片
	 * 
	 * @param attachment
	 */
	public Attachment saveAttachment(Attachment attachment);

	/**
	 * 根据图片id状态删除图片
	 * 
	 * @param
	 */
	public void removeAttachment(Integer attachmentId);

	/**
	 * 根据id修改图片信息（名字）
	 * 
	 * @param attachment
	 */
	public void modify(Attachment attachment);

	/**
	 * 
	 * 根据用户id查看头像附件
	 * 
	 * @param userId
	 * @return
	 */
	public Attachment showOne(Integer userId);

	/**
	 * 根据附件id查询单个附件
	 * 
	 * @param attachmentId
	 * @return
	 */
	public Attachment showAttachment(Integer attachmentId);





	public List<String> selectFileName(Integer[] id);
	public Attachment selectLastOne();
}
