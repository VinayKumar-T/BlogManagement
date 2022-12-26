package com.te.blogmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String email;
	private String passwordHash;
	private String intro;
	private String profile;

}
