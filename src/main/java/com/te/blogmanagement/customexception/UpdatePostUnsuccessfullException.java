package com.te.blogmanagement.customexception;

public class UpdatePostUnsuccessfullException extends RuntimeException {
	public UpdatePostUnsuccessfullException(String message) {
		super(message);
	}
}
