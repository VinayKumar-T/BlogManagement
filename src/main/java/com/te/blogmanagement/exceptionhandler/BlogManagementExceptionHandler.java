package com.te.blogmanagement.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.blogmanagement.customexception.UserRegistrationUnsuccessfullExcption;
import com.te.blogmanagement.customexception.DeletePostCommentUnsuccessfullException;
import com.te.blogmanagement.customexception.DeletePostUnsuccessfullException;
import com.te.blogmanagement.customexception.DeleteUserUnsuccessfullException;
import com.te.blogmanagement.customexception.LoginUnsuccessfullException;
import com.te.blogmanagement.customexception.PostAndCommentsUnAvailableException;
import com.te.blogmanagement.customexception.PostCommentRegistraionUnsuccessfullException;
import com.te.blogmanagement.customexception.PostUpdateUnSuccessfullException;
import com.te.blogmanagement.customexception.UpdatePostUnsuccessfullException;
import com.te.blogmanagement.customexception.UpdateUserUnsuccessfullException;
import com.te.blogmanagement.customexception.UserDataListUnAvailableException;
import com.te.blogmanagement.customexception.UserDataUnAvailableException;

@RestControllerAdvice
public class BlogManagementExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserRegistrationUnsuccessfullExcption.class)
	public Map<String, String> handler(UserRegistrationUnsuccessfullExcption registrationUnsuccessfullExcption) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", registrationUnsuccessfullExcption.getMessage());
		return map;

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(LoginUnsuccessfullException.class)
	public Map<String, String> handler(LoginUnsuccessfullException loginUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", loginUnsuccessfullException.getMessage());
		return map;

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UpdateUserUnsuccessfullException.class)
	public Map<String, String> handler(UpdateUserUnsuccessfullException updateUserUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", updateUserUnsuccessfullException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserDataUnAvailableException.class)
	public Map<String, String> handler(UserDataUnAvailableException userDataUnAvailableException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", userDataUnAvailableException.getMessage());
		return map;

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserDataListUnAvailableException.class)
	public Map<String, String> handler(UserDataListUnAvailableException userDataListUnAvailableException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", userDataListUnAvailableException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DeleteUserUnsuccessfullException.class)
	public Map<String, String> handler(DeleteUserUnsuccessfullException deleteUserUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", deleteUserUnsuccessfullException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PostUpdateUnSuccessfullException.class)
	public Map<String, String> handler(PostUpdateUnSuccessfullException postUpdateUnSuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", postUpdateUnSuccessfullException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DeletePostUnsuccessfullException.class)
	public Map<String, String> handler(DeletePostUnsuccessfullException deletePostUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", deletePostUnsuccessfullException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PostCommentRegistraionUnsuccessfullException.class)
	public Map<String, String> handler(
			PostCommentRegistraionUnsuccessfullException postCommentRegistraionUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", postCommentRegistraionUnsuccessfullException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UpdatePostUnsuccessfullException.class)
	public Map<String, String> handler(UpdatePostUnsuccessfullException updatePostUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("messgae", updatePostUnsuccessfullException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PostAndCommentsUnAvailableException.class)
	public Map<String, String> handler(PostAndCommentsUnAvailableException postAndCommentsUnAvailableException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", postAndCommentsUnAvailableException.getMessage());
		return map;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DeletePostCommentUnsuccessfullException.class)
	public Map<String, String> handler(
			DeletePostCommentUnsuccessfullException deletePostCommentUnsuccessfullException) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", deletePostCommentUnsuccessfullException.getMessage());
		return map;
	}
}
