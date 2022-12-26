package com.te.blogmanagement.customexception;

public class DeletePostCommentUnsuccessfullException extends RuntimeException {
	public DeletePostCommentUnsuccessfullException(String message) {
		super(message);
	}

}
