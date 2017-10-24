package com.friendfinder.util;

/**
 * 
 * 由于分页需要的代码基本一致，在此进行业务的封装，以后可直接使用开发(注意 : 此对象相当于封装的查询对象)
 * 但是设置属性时必须按照声明的顺序进行初始化
 * 
 * @author leaf
 * 
 */
public class SplitPageUtil {
	private Integer pageSize; // 单次查询记录的条数
	private Integer pagePos; // 需要查询的页码
	public SplitPageUtil() {
		super();
	}
	public Integer getPagePos() {
		return pagePos;
	}
	public void setPagePos(Integer pagePos) {
		if (pagePos < 0 || pageSize < 0) {
			throw new ArrayIndexOutOfBoundsException(); // 数组越界异常
		}
		this.pagePos = (pagePos - 1) * pageSize;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
			
}
