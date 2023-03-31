package com.a2m.lab.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.a2m.lab.entities.UserRoleEntity;

@Entity
public class UserDto {
	@Id
	private Long id;
	private String fullName;
	private String username;
	private String password;
	@OneToMany(targetEntity = UserRoleEntity.class)
	private List<UserRoleEntity> listRole;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id, String fullName, String username, String password, List<UserRoleEntity> listRole) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.listRole = listRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRoleEntity> getListRole() {
		return listRole;
	}

	public void setListRole(List<UserRoleEntity> listRole) {
		this.listRole = listRole;
	}
}
