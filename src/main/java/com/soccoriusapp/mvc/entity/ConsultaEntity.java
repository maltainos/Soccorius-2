package com.soccoriusapp.mvc.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "consulta_id", nullable = false, unique = true)
	private String consultaId;
	
	@Column(name = "consulta_tipo")
	private String consultaTipo;
	
	@Column(name = "consulta_nome")
	private String consultaNome;
	
	@ManyToMany
	@JoinTable(
			name = "pacientes_consultas", 
			joinColumns = @JoinColumn(name = "consulta_id"), 
			inverseJoinColumns = @JoinColumn(name = "paciente_id")
		)
	private Paciente paciente;
	
	private LocalDateTime dataConsulta;
}
