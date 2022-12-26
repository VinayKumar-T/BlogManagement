package com.te.blogmanagement.customexception;

public class UserDataUnAvailableException extends RuntimeException {
	public UserDataUnAvailableException(String message) {
		super(message);
	}
}
