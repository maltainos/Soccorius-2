package com.soccoriusapp.mvc.entity;

import java.time.LocalDate;

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
	private LocalDate createdOn;
	private LocalDate updatedOn;

}
