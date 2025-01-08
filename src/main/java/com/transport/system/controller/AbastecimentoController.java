package com.transport.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transport.system.dto.AbastecimentoRequestDTO;
import com.transport.system.dto.AbastecimentoResponseDTO;
import com.transport.system.service.AbastecimentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/abastecimentos")
@Tag(name = "Abastecimento", description = "API para gerenciamento de abastecimentos")
public class AbastecimentoController {

	@Autowired
	private AbastecimentoService service;

	@Operation(summary = "Criar novo abastecimento", description = "Cria um novo registro de abastecimento com os dados fornecidos")
	@ApiResponse(responseCode = "200", description = "Abastecimento criado com sucesso", content = @Content(schema = @Schema(implementation = AbastecimentoResponseDTO.class)))
	@ApiResponse(responseCode = "400", description = "Dados inválidos")
	@PostMapping
	public ResponseEntity<AbastecimentoResponseDTO> create(@Valid @RequestBody AbastecimentoRequestDTO request) {
		return ResponseEntity.ok(service.create(request));
	}

	@Operation(summary = "Atualizar abastecimento", description = "Atualiza um registro de abastecimento existente")
	@ApiResponse(responseCode = "200", description = "Abastecimento atualizado com sucesso")
	@ApiResponse(responseCode = "404", description = "Abastecimento não encontrado")
	@PutMapping("/{id}")
	public ResponseEntity<AbastecimentoResponseDTO> update(@PathVariable Long id,
			@Valid @RequestBody AbastecimentoRequestDTO request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@Operation(summary = "Listar abastecimentos", description = "Retorna uma lista paginada de abastecimentos com filtro opcional por placa")
	@GetMapping
	public ResponseEntity<Page<AbastecimentoResponseDTO>> list(@RequestParam(required = false) String placa,
			Pageable pageable) {
		return ResponseEntity.ok(service.list(placa, pageable));
	}

	@Operation(summary = "Deletar abastecimento", description = "Remove um registro de abastecimento pelo ID")
	@ApiResponse(responseCode = "204", description = "Abastecimento removido com sucesso")
	@ApiResponse(responseCode = "404", description = "Abastecimento não encontrado")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}