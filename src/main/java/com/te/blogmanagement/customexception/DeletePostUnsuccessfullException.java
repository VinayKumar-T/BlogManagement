package com.te.blogmanagement.customexception;

public class DeletePostUnsuccessfullException extends RuntimeException {
	public DeletePostUnsuccessfullException(String message) {
		super(message);
	}

}
