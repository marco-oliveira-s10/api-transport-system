package com.transport.system.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.system.model.Abastecimento;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
	Page<Abastecimento> findByPlacaContainingIgnoreCase(String placa, Pageable pageable);

	Optional<Abastecimento> findTopByPlacaOrderByQuilometragemDesc(String placa);
}