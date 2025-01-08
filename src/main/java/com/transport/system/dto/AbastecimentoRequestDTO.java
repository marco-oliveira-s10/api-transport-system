package com.transport.system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AbastecimentoRequestDTO {
	@NotNull(message = "Data e hora são obrigatórios")
	private LocalDateTime dataHora;

	@NotBlank(message = "Placa é obrigatória")
	@Pattern(regexp = "^[A-Z]{3}-\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$", message = "Placa deve estar no formato AAA-1234 ou ABC1D23")
	private String placa;

	@NotNull(message = "Quilometragem é obrigatória")
	@Min(value = 0, message = "Quilometragem deve ser maior que zero")
	private Integer quilometragem;

	@NotNull(message = "Valor total é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor total deve ser maior que zero")
	private BigDecimal valorTotal;
}
