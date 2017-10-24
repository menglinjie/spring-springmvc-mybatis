package com.friendfinder.exception;

/**
 * 自定义系统全局异常
 *
 */
public class ExtensionException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;
	
	public ExtensionException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
