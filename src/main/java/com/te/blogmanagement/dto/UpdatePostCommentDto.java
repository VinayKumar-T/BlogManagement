package com.te.blogmanagement.dto;

import java.time.LocalDateTime;

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
public class UpdatePostCommentDto {
	private String parentId;
	private String title;
	private String published;
	private LocalDateTime publishedAt;
	private String content;

	private PostDto postDto;
	
	

}
