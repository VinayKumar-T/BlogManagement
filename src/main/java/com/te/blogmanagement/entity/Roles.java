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
public class Roles {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private String roleName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "role_appuser_table", joinColumns = @JoinColumn(name = "role_fk"), inverseJoinColumns = @JoinColumn(name = "appuser_fk"))
	private List<AppUser> appUser = new ArrayList<AppUser>();
}
