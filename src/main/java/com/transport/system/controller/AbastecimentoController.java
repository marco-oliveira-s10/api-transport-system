// Controller Atualizado
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/abastecimentos")
public class AbastecimentoController {

	@Autowired
	private AbastecimentoService service;

	@PostMapping
	public ResponseEntity<AbastecimentoResponseDTO> create(@Valid @RequestBody AbastecimentoRequestDTO request) {
		return ResponseEntity.ok(service.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AbastecimentoResponseDTO> update(@PathVariable Long id,
			@Valid @RequestBody AbastecimentoRequestDTO request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@GetMapping
	public ResponseEntity<Page<AbastecimentoResponseDTO>> list(@RequestParam(required = false) String placa,
			Pageable pageable) {
		return ResponseEntity.ok(service.list(placa, pageable));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}