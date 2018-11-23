package com.hwua.exception;

import java.io.Serializable;

public class CustomException extends Exception implements Serializable {
	private static final long serialVersionUID = -885845784007196426L;
	// 异常信息
	private String message;

	public CustomException() {
		super();
	}

	public CustomException(String message) {
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
