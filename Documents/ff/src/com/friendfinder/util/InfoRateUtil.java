package com.friendfinder.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InfoRateUtil {
	
	public static Integer mainMethod(Class<?> c, Object o) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{		
		// 获取指定字段
		String[] s = {"id", "password", "portrait_id", "state"};
		List<Field> list = getAllFields(c, true, s);
		
		// 遍历字段，并进行对应方法的调用
		int sum = 0;
		for (Field f : list) {
//			System.out.println("get" + firstUpperCase(f.getName()));
			Method method = c.getDeclaredMethod("get" + firstUpperCase(f.getName()));	
			Object invoke = method.invoke(o);
			if(invoke != null && !invoke.toString().equals("")){
				sum += 10;
			} 
		}
//		System.out.println(sum);
		
		return sum;
	}

	/**
	 * 将字符串首字母转换为大写
	 * 
	 * @param source
	 * @return
	 */
	public static String firstUpperCase(String source){
		return source.substring(0, 1).toUpperCase() + source.substring(1, source.length()); 
	}
	
	/**
	 * 返回对象中所有的属性字段
	 * 
	 * @param c 类对象
	 * @param flag true代表"非",false代表"交集"
	 * @param a 字符串列表(属性字段的子集)
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> c, Boolean flag, String[] a){
		List<Field> list = new ArrayList<Field>();
		Field[] fields = c.getDeclaredFields();
		
		if (flag) {
			for (int i = 0; i < fields.length; i++) {
				boolean temp = false;
				for (int j = 0; j < a.length; j++) {
					if (fields[i].getName().equals(a[j])) {
						temp = true;
					}
				}
				if (!temp) {
					list.add(fields[i]);
				}
			}
		} else {
			for (int i = 0; i < fields.length; i++) {
				boolean temp = false;
				for (int j = 0; j < a.length; j++) {
					if (fields[i].getName().equals(a[j])) {
						temp = true;
					}
				}
				if (temp) {
					list.add(fields[i]);
				}
			}
		}
		
		return list;
	}
}
