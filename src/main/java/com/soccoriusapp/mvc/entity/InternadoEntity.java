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
@Table(name = "internados")
public class InternadoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "internado_id", length = 35, nullable = false)
	private String internadoId;
	
	@Column(nullable = false)
	private int numeroAndar = 1;
	
	@Column( nullable = false)
	private int numeroSala = 1;
	
	@Column(nullable = false)
	private int numeroCama = 1;
	
	@Column(name = "status", columnDefinition = "Boolean default true")
	private Boolean status = true;
	
	@Column(name = "data_internamento", nullable = false)
	@DateTimeFormat(pattern = "d, MMM uuuu, HH:mm:ss")
	private LocalDateTime dataInternamento = LocalDateTime.now();
	
	private LocalDateTime dataAlta;
	
	@ManyToOne
	@JoinColumn(name = "triagem_id")
	private Triagem triagem;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity medicoResponsavel;
}
