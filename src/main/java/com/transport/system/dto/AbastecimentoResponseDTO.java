package com.transport.system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.transport.system.model.Abastecimento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de resposta com dados do abastecimento")
public class AbastecimentoResponseDTO {
	@Schema(description = "ID do abastecimento", example = "1")
	private Long id;

	@Schema(description = "Data e hora do abastecimento", example = "2024-01-08T10:00:00")
	private LocalDateTime dataHora;

	@Schema(description = "Placa do veículo", example = "ABC-1234")
	private String placa;

	@Schema(description = "Quilometragem do veículo", example = "1000")
	private Integer quilometragem;

	@Schema(description = "Valor total do abastecimento", example = "150.00")
	private BigDecimal valorTotal;

	public static AbastecimentoResponseDTO fromEntity(Abastecimento abastecimento) {
		AbastecimentoResponseDTO dto = new AbastecimentoResponseDTO();
		dto.setId(abastecimento.getId());
		dto.setDataHora(abastecimento.getDataHora());
		dto.setPlaca(abastecimento.getPlaca());
		dto.setQuilometragem(abastecimento.getQuilometragem());
		dto.setValorTotal(abastecimento.getValorTotal());
		return dto;
	}

}