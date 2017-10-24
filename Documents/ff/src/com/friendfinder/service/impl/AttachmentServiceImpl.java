package com.friendfinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendfinder.dao.AttachmentDao;
import com.friendfinder.model.Attachment;
import com.friendfinder.service.AttachmentService;

import java.util.List;

/**
 * 图片服务层
 * 
 * @author lenovo
 *
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment saveAttachment(Attachment attachment) {
		attachmentDao.insertAttachment(attachment);
		return attachmentDao.selectLastOne();
	}

	@Override
	public void removeAttachment(Integer attachmentId) {

		attachmentDao.remove(attachmentId);
	}

	@Override
	public void modify(Attachment attachment) {

		attachmentDao.updateattachment(attachment);
	}

	@Override
	public Attachment showOne(Integer userId) {

		Attachment attachment = attachmentDao.selectOne(userId);
		return attachment;
	}

	@Override
	public Attachment showAttachment(Integer attachmentId) {
		return attachmentDao.selectAttachment(attachmentId);

	}

	@Override
    public List<String> selectFileName(Integer[] id){
	    return attachmentDao.selectFileName(id);
    }

	@Override
	public Attachment selectLastOne() {
		return attachmentDao.selectLastOne();
	};

}
