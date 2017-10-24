package com.friendfinder.test;

import java.io.File;

import org.junit.Test;

import com.friendfinder.util.UploadFileUtil;

public class UtilTest {

	@Test
	public void testUpload() {

		UploadFileUtil upload = new UploadFileUtil();


		String nativeName="1.jpg";
		boolean b = upload.upload(new File("D:/test/1.jpg"));
	
		/*
		File file = new File("D:/test/1.jpg");

		boolean b = upload.upload(file);
*/
		System.out.println(b);

		String newFileName = upload.getNewFileName();
		System.out.println(upload.getAllPathName(nativeName));
		System.out.println(newFileName);
	}
}
