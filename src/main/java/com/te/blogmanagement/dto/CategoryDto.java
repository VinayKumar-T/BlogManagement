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

public class CategoryDto {
	private Integer parentId;
	private String title;
	private String metaTitle;
	private String slug;
	private String content;

	private List<PostDto> postDto = new ArrayList<PostDto>();

}
