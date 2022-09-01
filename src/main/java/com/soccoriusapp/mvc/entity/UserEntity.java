package com.soccoriusapp.mvc.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 128)
	private String email;
	
	@Column(nullable = false, length = 64)
	private String firstName;
	
	@Column(nullable = false, length = 128)
	private String lastName;
	
	@Column(nullable = false, columnDefinition="Boolean default false")
	private Boolean emailVerificationStatus = false;
	
	@Column(nullable = false, length = 64)
	private String encryptPassword;
	
	@Column(nullable = false,  length = 128)
	private String image;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdOn;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate updatedOn;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", 
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();
	
	public UserEntity(Integer id, String encryptPassword) {
		this.id = id;
		this.encryptPassword = encryptPassword;
	}
	
	@Transient
	public void addRole(RoleEntity role) {
		this.roles.add(role);
	}
	
	@Transient
	public String getFullName() {
		return firstName +" "+lastName;
	}
}
