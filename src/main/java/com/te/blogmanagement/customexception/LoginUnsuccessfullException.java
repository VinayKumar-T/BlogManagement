package com.te.blogmanagement.customexception;

public class LoginUnsuccessfullException extends RuntimeException {
	public LoginUnsuccessfullException(String message) {
		super(message);
	}

}
