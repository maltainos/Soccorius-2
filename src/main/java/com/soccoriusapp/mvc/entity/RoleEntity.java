package com.soccoriusapp.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 128)
	private String roleName;
	
	@Column(columnDefinition="text")
	private String roleDescription;
	
	public RoleEntity(String roleName){
		this.roleName = roleName;
	}

	public RoleEntity(String roleName, String roleDescription){
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	
	public String toString() {
		return roleName;
	}

}
