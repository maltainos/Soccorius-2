package com.soccoriusapp.mvc.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "triagens")
public class Triagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private int triagemNumber;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@Column(nullable = false, length = 256)
	private String sintomas;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity assistente;
	
	@Column(name = "triagem_status", columnDefinition = "Boolean default false")
	private Boolean triagemStatus = false;

	@DateTimeFormat(pattern = "d, MMM uuuu, HH:mm:ss")
	private LocalDateTime createAt = LocalDateTime.now();
	
	private LocalDateTime updateAt;

}
