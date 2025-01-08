package com.transport.system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "DTO para requisição de criação/atualização de abastecimento")
public class AbastecimentoRequestDTO {

	@Schema(description = "Data e hora do abastecimento", example = "2024-01-08T10:00:00")
	@NotNull(message = "Data e hora são obrigatórios")
	private LocalDateTime dataHora;

	@Schema(description = "Placa do veículo", example = "ABC-1234")
	@NotBlank(message = "Placa é obrigatória")
	@Pattern(regexp = "^[A-Z]{3}-\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$", message = "Placa deve estar no formato AAA-1234 ou ABC1D23")
	private String placa;

	@Schema(description = "Quilometragem do veículo", example = "1000")
	@NotNull(message = "Quilometragem é obrigatória")
	@Min(value = 0, message = "Quilometragem deve ser maior que zero")
	private Integer quilometragem;

	@Schema(description = "Valor total do abastecimento", example = "150.00")
	@NotNull(message = "Valor total é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor total deve ser maior que zero")
	private BigDecimal valorTotal;
}