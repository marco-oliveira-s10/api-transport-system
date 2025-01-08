package com.transport.system.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transport.system.dto.AbastecimentoRequestDTO;
import com.transport.system.dto.AbastecimentoResponseDTO;
import com.transport.system.exception.BusinessException;
import com.transport.system.model.Abastecimento;
import com.transport.system.repository.AbastecimentoRepository;

@Service
public class AbastecimentoService {

	@Autowired
	private AbastecimentoRepository repository;

	@Transactional
	public AbastecimentoResponseDTO create(AbastecimentoRequestDTO request) {
		validateAbastecimento(request);

		Abastecimento abastecimento = new Abastecimento(request.getDataHora(), request.getPlaca(),
				request.getQuilometragem(), request.getValorTotal());

		return AbastecimentoResponseDTO.fromEntity(repository.save(abastecimento));
	}

	@Transactional
	public AbastecimentoResponseDTO update(Long id, AbastecimentoRequestDTO request) {
		Abastecimento abastecimento = repository.findById(id)
				.orElseThrow(() -> new BusinessException("Abastecimento não encontrado"));

		validateAbastecimento(request);

		abastecimento.setDataHora(request.getDataHora());
		abastecimento.setPlaca(request.getPlaca());
		abastecimento.setQuilometragem(request.getQuilometragem());
		abastecimento.setValorTotal(request.getValorTotal());

		return AbastecimentoResponseDTO.fromEntity(repository.save(abastecimento));
	}

	public Page<AbastecimentoResponseDTO> list(String placa, Pageable pageable) {
		Page<Abastecimento> abastecimentos;
		if (placa != null && !placa.isEmpty()) {
			abastecimentos = repository.findByPlacaContainingIgnoreCase(placa, pageable);
		} else {
			abastecimentos = repository.findAll(pageable);
		}

		return abastecimentos.map(AbastecimentoResponseDTO::fromEntity);
	}

	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new BusinessException("Abastecimento não encontrado");
		}
		repository.deleteById(id);
	}

	private void validateAbastecimento(AbastecimentoRequestDTO request) {
		if (request.getDataHora().isAfter(LocalDateTime.now())) {
			throw new BusinessException("Data e hora não podem ser futuras");
		}

		repository.findTopByPlacaOrderByQuilometragemDesc(request.getPlaca()).ifPresent(ultimoAbastecimento -> {
			if (request.getQuilometragem() <= ultimoAbastecimento.getQuilometragem()) {
				throw new BusinessException("Quilometragem deve ser maior que o último abastecimento: "
						+ ultimoAbastecimento.getQuilometragem());
			}
		});
	}
}
