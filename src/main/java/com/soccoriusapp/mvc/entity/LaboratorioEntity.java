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
@Table(name = "laboratorios")
public class LaboratorioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "serie_amostra", nullable = false, unique = true, length = 10)
	private String serieAmostra;
	
	@Column(name = "resultado_amostra", nullable = true)
	private String resultadoAmostra;
	
	@Column(name = "tipo_exame", nullable = false)
	private String tipoExame;
	
	@Column(name = "status", columnDefinition = "Boolean default false")
	private boolean status = false;
	
	@DateTimeFormat(pattern = "d, MMM uuuu, HH:mm:ss")
	@Column(name = "hora_entrada", nullable = false)
	private LocalDateTime horaEntrada;
	
	@DateTimeFormat(pattern = "d, MMM uuuu, HH:mm:ss")
	private LocalDateTime horaSaida;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private UserEntity medicoRequisitante;
	
	@ManyToOne
	@JoinColumn(name = "laboratorista_id")
	private UserEntity laboratorista;
	
	@ManyToOne
	@JoinColumn(name = "triagem_id")
	private Triagem triagem;

}
