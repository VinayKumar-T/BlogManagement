package com.te.blogmanagement.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
	private String message;
	private String token;
	private T data;

	public ApiResponse(String message, String token, T data) {
		super();
		this.message = message;
		this.token = token;
		this.data = data;
	}

}
