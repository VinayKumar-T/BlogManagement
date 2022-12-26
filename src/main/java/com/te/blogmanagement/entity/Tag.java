package com.te.blogmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String metaTitle;
	private String slug;
	private String content;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tag_post_table", joinColumns = @JoinColumn(name = "tag_fk"), inverseJoinColumns = @JoinColumn(name = "post_fk"))
	private List<Post> post = new ArrayList<Post>();
}
