package com.te.blogmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

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
public class AppUser {
	@Id
	private String userName;
	private String password;

	@ManyToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
	private List<Roles> roles = new ArrayList<Roles>();

}
