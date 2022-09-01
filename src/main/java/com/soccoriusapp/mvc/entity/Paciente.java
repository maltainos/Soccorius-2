package com.soccoriusapp.mvc.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "pacientes")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String pacienteCode;
	
	private String firstName;

	private String lastName;
	
	private String fathersName;
	
	private String mothersName;
	
	private String email;
	
	private String address;
	
	private String phone;
	
	private int yearsOld;
	
	private String placeOfBirth;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	private LocalDate createdOn;
	
	private LocalDate updatedOn;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Triagem> triagens = new HashSet<>();
	
	@Transient
	public String getPacienteTriagemForm() {
		return pacienteCode+" - "+firstName+" "+lastName;
	}
	
	@Transient
	public String getCodeFullNameAge() {
		return pacienteCode+" - "+firstName+" "+lastName+" - "+yearsOld+" Anos";
	}
	
	@Transient
	public String getFullName() {
		return firstName+" "+lastName;
	}
}
