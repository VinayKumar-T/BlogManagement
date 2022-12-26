package com.te.blogmanagement.customexception;

public class PostUpdateUnSuccessfullException extends RuntimeException {
	public PostUpdateUnSuccessfullException(String message) {
		super(message);
	}
}
