package com.te.blogmanagement.customexception;

public class DeleteUserUnsuccessfullException extends RuntimeException {
public DeleteUserUnsuccessfullException(String message) {
	super(message);
}
}
