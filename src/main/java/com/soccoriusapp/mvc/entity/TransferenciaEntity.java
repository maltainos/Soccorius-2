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
@Table(name = "transferencias")
public class TransferenciaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "transferencia_id", nullable = false, unique = true, length = 35)
	private String transferenciaId;
	
	@Column(name = "descricao", columnDefinition = "text")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@Column(name = "tranfereido_para")
	private String transferidoPara;
	
	@Column(name = "data_transferencia")
	@DateTimeFormat(pattern = "d, MMM uuuu, HH:mm:ss")
	private LocalDateTime dataTransferencia = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity medicoResponsavel;
	
	@ManyToOne
	@JoinColumn(name = "triagem_id")
	private Triagem triagem;

}
