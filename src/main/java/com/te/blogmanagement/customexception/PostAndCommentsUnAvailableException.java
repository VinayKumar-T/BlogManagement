package com.te.blogmanagement.customexception;

public class PostAndCommentsUnAvailableException extends RuntimeException {
	public PostAndCommentsUnAvailableException(String message) {
		super(message);
	}

}
