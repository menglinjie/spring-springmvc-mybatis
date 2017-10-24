package com.friendfinder.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码生成工具
 * 
 * @author Mr.leaf
 * 
 * */
public class CycleImageUtil {
	
	private int imgWidth = 110; // 设置默认图片宽度
	private int imgHeight = 30; // 设置默认图片高度
	private int codeCount = 4; // 设置默认验证码位数
	
	private int codeX; // 验证码中字符位的x轴位置
	private int codeY; // 验证码中字符为的y轴位置
	
	private String result = ""; // 生成结果
	private BufferedImage image = null; // 图像
	
	private Random random = new Random(); // 生成随机类
	
	public CycleImageUtil() {
		this.codeX = this.imgWidth / (this.codeCount + 1); //设置每个验证码的宽度
		this.codeY = this.imgHeight - 12; //设置每个验证码的高度
	}
	
	//返回验证码结果
	public String getResult() {
		return result;
	}

	//返回图像
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * 绘制验证码图片
	 * */
	public void drawImage() {
		//在内存中创建图像
		this.image = new BufferedImage(this.imgWidth, this.imgHeight, BufferedImage.TYPE_INT_RGB);
		//获取图像上下文
		Graphics2D g = this.image.createGraphics();
		
		//准备图片的背景颜色
		g.setColor(Color.YELLOW);
		//填充实心的矩形
		g.fillRect(0, 0, this.imgWidth, this.imgHeight);
			
		//准备画边框的颜色
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
		//开始画边框
		g.drawRect(0, 0, this.imgWidth - 1, this.imgHeight - 1);
			
		//生成随机干扰线
		for (int i = 0; i < 8; i++) {
			int x = random.nextInt(this.imgWidth);
			int y = random.nextInt(this.imgHeight);
			int x1 = random.nextInt(this.imgWidth);
			int y1 = random.nextInt(this.imgHeight);
			//开始准备干扰线的颜色
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawLine(x, y, x1, y1);
		}
		
		//开始随机生成验证码
		StringBuffer cycleCode = new StringBuffer();
		//随机颜色
		int red = 0, green = 0, blue = 0;
		for (int i = 0; i < this.codeCount; i++) {
			//设置字体
			g.setFont(this.getFontStyle());
			//随机生成颜色
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			
			//随机确定验证码类型
			int wordType = random.nextInt(3);
			//记录生成的字符或数字
			int retWord = 0;
			switch (wordType) {
			case 0 :
				retWord = this.getRandomNumber();
				break;
			case 1 :
				retWord = this.getRandomChar(0); // 大写字母
				break;
			case 2 : 
				retWord = this.getRandomChar(1); // 小写字母
				break;
			}
			//将生成的字符或数字添加至结果中
			cycleCode.append((char)retWord);
			//准备画字符或数字的颜色
			g.setColor(new Color(red, green, blue));
			//开始画字符
			g.drawString(String.valueOf((char)retWord), (i) * this.codeX, this.codeY);
		}
		//记录结果
		this.result = cycleCode.toString();
	}
	
	/**
	 * 随机生成字体样式
	 * */
	private Font getFontStyle(){
		//定义字体名字
		String[] fontNames = {"Times New Roman", "Consolas", "Courier New", "宋体", "Corbel"};
		//定义字体样式，分别对应平体、粗体、斜体
		int[] fontStyles = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.PLAIN + Font.BOLD, Font.PLAIN + Font.ITALIC, Font.BOLD + Font.ITALIC};
		//随机返回字体样式
		return new Font(fontNames[this.random.nextInt(fontNames.length)], fontStyles[this.random.nextInt(fontStyles.length)], this.setFontHeight());
	}
	
	/**
	 * 随机计算字体的高度，尽量保证每个验证码字体高度不一样，
	 * 但是均不能超过图像高度，若过大或过小则默认等于默认值
	 * */
	private int setFontHeight(){
		//获取原图像高度
		int height = this.imgHeight;
		//随机生成字体高度
		int tempHeight = random.nextInt(this.imgHeight);
		//生成字体高度过大或过小时，设置默认值
		if (tempHeight == 0 || tempHeight >= height || tempHeight <= (height / 2)) {
			tempHeight = height;
		}
		return tempHeight;
	}
	
	/**
	 * 随机生成0-9的数字
	 * */
	private char getRandomNumber() {
		Random random = new Random();
		int numberResult = random.nextInt(10);
		// 由于在ASCII下，0是48，所以加上48
		int result = numberResult + 48;
		return (char)result;
	}
	
	/**
	 * 随机生成大小写字母
	 * @param upper 判断生成字符为大写还是小写
	 * */
	private char getRandomChar(int upper) {
		Random random = new Random();
		int numResult = random.nextInt(26);
		int result = 0;
		if (upper == 0) {// 小写判断
			result = numResult + 97;
		} else if (upper == 1) {// 大写判断
			result = numResult + 65;
		}
		return (char)result;
	}
	
	/**
	 * 将图片进行Base64编码，返回编码后的结果
	 * 
	 * */
	public String encodeImage(RenderedImage image) {
		//生成字节数组流，并将图片写入到字节数组流中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  	
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//将字节数组流转换为字节数组，并调用编码方法返回结果值
		byte[] byteArray = baos.toByteArray();
		return encodeBase64(byteArray);
	}
	
	/**
	 * 将字节数组进行Base64编码(使用反射加载Base64编码方法)
	 * 
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String encodeBase64(byte[] input) {
		//加载反射类
		String className = "com.sun.org.apache.xerces.internal.impl.dv.util.Base64";
		Class clazz;
		Object retObj = null;
		try {
			clazz = Class.forName(className);
			//获取编码方法	
			Method mainMethod = clazz.getMethod("encode", byte[].class);
			mainMethod.setAccessible(true);
			//执行Base64编码方法
			retObj = mainMethod.invoke(null, new Object[]{input});
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return (String)retObj;
	}

	/**
	 * 将字符串进行Base64解码(使用反射加载Base64解码方法)
	 * 
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] decodeBase64(String input){
		//加载反射类
		String className = "com.sun.org.apache.xerces.internal.impl.dv.util.Base64";
		Class clazz;
		Object retObj = null;
		try {
			clazz = Class.forName(className);
			//获取解码方法
			Method mainMethod = clazz.getMethod("decode", String.class);
			mainMethod.setAccessible(true);	
			//执行Base64解码方法
			retObj = mainMethod.invoke(null, input);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (byte[])retObj;
	}
}
