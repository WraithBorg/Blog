package com.zxu.demo.java8.lock;

/**
 * @author Administrator
 */
public class ReturnResult {
	private boolean result;
	private String message;

	public ReturnResult(boolean result) {
		this.result = result;
	}

	public ReturnResult(boolean result, String message) {
		this.result = result;
		this.message = message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
