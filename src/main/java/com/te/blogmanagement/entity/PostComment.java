package com.te.blogmanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.te.blogmanagement.enums.Status;

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
@Entity
public class PostComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "posts_Id")
	private String postId;
	private String parentId;
	private String title;
	private String published;
	private LocalDateTime createdAt;
	private LocalDateTime publishedAt;
	private String content;
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(cascade = CascadeType.ALL)
	private Post post;

}
