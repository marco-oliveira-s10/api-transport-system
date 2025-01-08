package com.transport.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "abastecimentos")
@Data
public class Abastecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime dataHora;

	@Column(nullable = false, length = 8)
	private String placa;

	@Column(nullable = false)
	private Integer quilometragem;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	// Constructors
	public Abastecimento() {
	}

	public Abastecimento(LocalDateTime dataHora, String placa, Integer quilometragem, BigDecimal valorTotal) {
		this.dataHora = dataHora;
		this.placa = placa;
		this.quilometragem = quilometragem;
		this.valorTotal = valorTotal;
	}
}