package com.te.blogmanagement.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
public class Post {
	@Id
	@GeneratedValue(generator = "post_gen", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "post_gen",sequenceName = "post_seq",initialValue = 1,allocationSize =1)
	private Integer id;
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
	@Enumerated(EnumType.STRING)
	private Status postStatus;
	

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_fk")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostComment> postComment = new ArrayList<PostComment>();

	@ManyToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Category> category = new ArrayList<Category>();

	@ManyToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Tag> tag = new ArrayList<Tag>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostMeta> postMeta = new ArrayList<PostMeta>();

}
