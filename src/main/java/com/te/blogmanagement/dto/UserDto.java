package com.te.blogmanagement.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String email;
	private String passwordHash;
	private String intro;
	private String profile;

	private List<PostDto> postDto = new ArrayList<PostDto>();

}
