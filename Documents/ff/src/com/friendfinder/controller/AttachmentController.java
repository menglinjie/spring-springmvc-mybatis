package com.friendfinder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.friendfinder.model.Attachment;
import com.friendfinder.model.User;
import com.friendfinder.service.AttachmentService;
import com.friendfinder.service.UserService;
import com.friendfinder.util.UploadFileUtil;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private UserService userService;

	/**
	 * 保存图片
	 * 
	 * @param attachment
	 */
	@Transactional
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public Attachment save(@RequestBody MultipartFile file) {

		UploadFileUtil uploadFileUtil = new UploadFileUtil();
		boolean upload = uploadFileUtil.upload(file);

		String nativeName = file.getOriginalFilename();
		System.out.println(nativeName);
		String type = file.getContentType();
		String fileName = uploadFileUtil.getNewFileName();

		Attachment attachment = new Attachment();
		attachment.setNativeName(nativeName);
		attachment.setFileName(fileName);
		attachment.setFileType(type);
		System.out.println(fileName);
	
		return attachmentService.saveAttachment(attachment);
	}
	
	@Transactional
	@RequestMapping(value = "/updateUser.action", method = RequestMethod.POST)
	public @ResponseBody Attachment updateUserAttachment(HttpServletRequest req, MultipartFile file){
		UploadFileUtil uploadFileUtil = new UploadFileUtil();
		boolean upload = uploadFileUtil.upload(file);

		String nativeName = file.getOriginalFilename();
		String type = file.getContentType();
		String fileName = uploadFileUtil.getNewFileName();

		Attachment attachment = new Attachment();
		attachment.setNativeName(nativeName);
		attachment.setFileName(fileName);
		attachment.setFileType(type);
		System.out.println(fileName);
		
		Integer userId = (Integer)req.getSession(true).getAttribute("id");
		Attachment a = attachmentService.saveAttachment(attachment);
		Integer id = a.getId();
		
		
		User u = userService.findById(userId);
		if (u != null) {
			u.setPortraitId(id);
		}
		
		userService.update(u);
		
		return a;
	}

	/**
	 * 根据id状态删除附件
	 * 
	 * http://localhost:8888/friend_finder/attachment/remove?attachmentId=5	
	 * 
	 * 
	 * @param attachmentId
	 */
	@Transactional
	@RequestMapping(value = "/remove.action", method = RequestMethod.DELETE)
	public void remove(@RequestParam Integer attachmentId) {

		attachmentService.removeAttachment(attachmentId);
	}

	/**
	 * 根据id修改图片信息(图片名字) 修改后名字，id
	 * 
	 * @param attachment
	 */
	@Transactional
	@RequestMapping(value = "/modify.action", method = RequestMethod.POST)
	public void modify(@RequestBody Attachment attachment) {

		attachmentService.modify(attachment);
	}
	
	
	

	/**
	 * 根据用户id查看头像
	 * 
	 * 测试http://localhost:8888/friend_finder/attachment/showPicture?userId=1
	 * {
		  "createTime": 1494575134365,
		  "fileName": "289e9256-44ab-4777-85c9-2097d047e6c0297.jpg",
		  "id": 4,
		  "nativeName": "1.jpg"
		}
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/showPicture.action", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Attachment showPicture(@RequestParam Integer userId) {
		return attachmentService.showOne(userId);
	}

	
	
	
	
	/**
	 * 根据附件id获取单个附件
	 * 
	 * 测试http://localhost:8888/friend_finder/attachment/showAttachment?attachmentId=4
	 * {
		  "createTime": 1494576065451,
		  "fileName": "289e9256-44ab-4777-85c9-2097d047e6c0297.jpg",
		  "id": 4,
		  "nativeName": "1.jpg"
		}.
	 * 
	 * @param attachmentId
	 * @return
	 */
	@RequestMapping(value = "/showAttachment.action", method = RequestMethod.GET)
	public Attachment showAttachment(@RequestParam Integer attachmentId) {
		return attachmentService.showAttachment(attachmentId);
		
	}
}
