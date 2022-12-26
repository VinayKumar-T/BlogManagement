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
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.checkerframework.common.aliasing.qual.Unique;

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
public class User {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * @GeneratedValue(generator = "uuid")
	 * 
	 * @GenericGenerator(name = "uuid", strategy = "uuid2")
	 */
	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	 * 
	 * @GenericGenerator(name = "book_seq", strategy =
	 * "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
	 * parameters = {
	 * 
	 * @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value =
	 * "50"),
	 * 
	 * @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER,
	 * value = "B_"),
	 * 
	 * @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,
	 * value = "%05d") })
	 */
	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	@Unique
	private String mobile;
	private String email;
	private String passwordHash;
	private LocalDateTime registeredAt;
	private LocalDateTime lastLogin;
	private String intro;
	private String profile;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Lob
	private byte[] data;

	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> post = new ArrayList<Post>();

}
