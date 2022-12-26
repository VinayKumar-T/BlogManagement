package com.te.blogmanagement.dto;

import java.time.LocalDateTime;
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
public class PostDto {
	private Integer authorId;
	private Integer parentId;
	private String title;
	private String metaTitle;
	private String slug;
	private String summary;
	private String published;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime publishedAt;
	private String content;

	private UserDto userDto;

	private List<PostCommentDto> postCommentDto=new ArrayList<PostCommentDto>();

	private List<CategoryDto> categoryDto = new ArrayList<CategoryDto>();

	private List<TagDto> tagDto = new ArrayList<TagDto>();

	private List<PostMetaDto> postMetaDto=new ArrayList<PostMetaDto>();
}
