package com.friendfinder.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * 本工具是对jersey的二次封装，旨在提高开发效率，主要功能如下(水平有限，不支持多线程)：
 * <p>
 * 1.获取文件后缀名(包括分隔符".")
 * </p>
 * <p>
 * 2.获取文件随机名(文件名通过系统UUID工具随机生成并附加三个随机数)
 * </p>
 * <p>
 * 3.获取文件全路径地址(包括服务器的地址)
 * </p>
 * <p>
 * 3.MultipartFile类型和File类型单文件上传方法
 * </p>
 * <p>
 * 4.文件大小校验，超过指定字节不予上传
 * </p>
 * 
 * 2017-4-28 17:56:10
 */
public class UploadFileUtil {
	private static Client client = Client.create(); // 图片服务器连接对象
	private WebResource resource; // 上传文件资源对象
	private static String urlAddress = "http://139.199.16.239:8080/demo/image"; // 初始化默认连接地址
	private static Object fileObject = null; // 上传文件对象
	private static String newName = null; // 新文件名(注意：此文件名是"一次性"的，不能永久保存)
	private long fileSize = 5 * 1024 * 1024; // 默认上传文件大小5M，大小为字节

	/**
	 * 无参构造方法
	 */
	public UploadFileUtil() {
	}

	/**
	 * 有参构造方法--指定上传地址和上传文件大小
	 */
	public UploadFileUtil(URL newAddress, int fileSize) {
		this.fileSize = fileSize;
		urlAddress = newAddress.getProtocol() + "://" + newAddress.getHost() + ":" + newAddress.getPort()
				+ newAddress.getPath(); // 指定上传路径
	}

	/**
	 * 单文件上传
	 * 
	 * @param uploadFile
	 *            上传文件
	 * @return boolean true代表上传成功，false代表上传失败
	 */
	public boolean upload(MultipartFile uploadFile) {
		// 超出指定大小，直接false返回
		if (uploadFile.getSize() > this.fileSize) {
			newName = "";
			return false;
		}
		fileObject = uploadFile;
		newName = this.getName(); // 生成新文件名
		this.resource = client.resource(urlAddress + "/" + newName); // 设置上传路径
		try {
			resource.put(String.class, uploadFile.getBytes()); // 上传文件
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 单文件上传
	 * 
	 * @param uploadFile
	 *            上传文件
	 * @return boolean true代表上传成功，false代表上传失败
	 */
	public boolean upload(File uploadFile) {
		fileObject = uploadFile;
		newName = this.getName(); // 生成新文件名
		this.resource = client.resource(urlAddress + "/" + newName); // 设置上传路径
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(uploadFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = fis.read(b)) != -1) {
				bos.write(b, 0, len); // 将图片添加至数组字节流中
			}
			// 当字节数组输出流超出指定大小时，直接false返回
			if (bos.size() > this.fileSize) {
				bos.close();
				newName = "";
				return false;
			}
			resource.put(String.class, bos.toByteArray()); // 上传文件
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	/**
	 * 获取上传文件的新名字(格式：UUID + 随机三位数字 + 原文件后缀名)
	 */
	public String getNewFileName() {
		return newName;
	}

	/**
	 * 生成新文件名(格式：UUID + 随机三位数字 + 原文件后缀名)
	 */
	private String getName() {
		String newName = null;
		StringBuilder sb = new StringBuilder();
		Random ran = new Random();
		// 生成三位随机数
		for (int i = 0; i < 3; i++) {
			sb.append(ran.nextInt(10));
		}
		// 设置新文件名
		if (getExtName() != null) {
			newName = UUID.randomUUID().toString() + sb + getExtName();
		}

		return newName;
	}

	/**
	 * 获取上传文件的完整路径
	 */
	public String getAllPathName() {
		return urlAddress + "/" + this.getNewFileName();
	}

	/**
	 * 根据文件名，拿到此文件的完整路径
	 */
	public static String getAllPathName(String name) {
		return urlAddress + "/" + name;
	}

	/**
	 * 获取文件后缀名
	 */
	public String getExtName() {
		String extName = null, fileName = null;
		if (fileObject != null) {
			// 获取文件名
			if (fileObject instanceof MultipartFile) {
				MultipartFile mu = (MultipartFile) fileObject;
				fileName = mu.getOriginalFilename();
			}
			if (fileObject instanceof File) {
				File f = (File) fileObject;
				fileName = f.getName();
			}
			// 获取文件后缀名
			extName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		} else {
			throw new NullPointerException();
		}
		return extName;
	}
}