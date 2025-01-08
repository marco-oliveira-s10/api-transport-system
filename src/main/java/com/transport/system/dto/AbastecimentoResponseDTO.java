package com.transport.system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.transport.system.model.Abastecimento;

import lombok.Data;

@Data
public class AbastecimentoResponseDTO {
	private Long id;
	private LocalDateTime dataHora;
	private String placa;
	private Integer quilometragem;
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