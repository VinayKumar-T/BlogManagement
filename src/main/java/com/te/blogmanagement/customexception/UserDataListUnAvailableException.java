package com.te.blogmanagement.customexception;

public class UserDataListUnAvailableException extends RuntimeException {
	public UserDataListUnAvailableException(String message) {
		super(message);
	}

}
