package com.transport.system;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.transport.system.dto.AbastecimentoRequestDTO;
import com.transport.system.dto.AbastecimentoResponseDTO;
import com.transport.system.exception.BusinessException;
import com.transport.system.model.Abastecimento;
import com.transport.system.repository.AbastecimentoRepository;
import com.transport.system.service.AbastecimentoService;

@ExtendWith(MockitoExtension.class)
public class AbastecimentoServiceTest {

	@Mock
	private AbastecimentoRepository repository;

	@InjectMocks
	private AbastecimentoService service;

	private AbastecimentoRequestDTO validRequest;
	private Abastecimento abastecimento;
	private LocalDateTime now;

	@BeforeEach
	void setUp() {
		now = LocalDateTime.now();
		validRequest = new AbastecimentoRequestDTO();
		validRequest.setDataHora(now);
		validRequest.setPlaca("ABC-1234");
		validRequest.setQuilometragem(1000);
		validRequest.setValorTotal(new BigDecimal("100.00"));

		abastecimento = new Abastecimento();
		abastecimento.setId(1L);
		abastecimento.setDataHora(now);
		abastecimento.setPlaca("ABC-1234");
		abastecimento.setQuilometragem(1000);
		abastecimento.setValorTotal(new BigDecimal("100.00"));
	}

	@Test
	void createSuccess() {
		// Arrange
		when(repository.findTopByPlacaOrderByQuilometragemDesc(anyString())).thenReturn(Optional.empty());
		when(repository.save(any(Abastecimento.class))).thenReturn(abastecimento);

		// Act
		AbastecimentoResponseDTO response = service.create(validRequest);

		// Assert
		assertNotNull(response);
		assertEquals(validRequest.getPlaca(), response.getPlaca());
		assertEquals(validRequest.getQuilometragem(), response.getQuilometragem());
		verify(repository, times(1)).save(any(Abastecimento.class));
	}

	@Test
	void createWithFutureDateShouldThrowException() {
		// Arrange
		validRequest.setDataHora(LocalDateTime.now().plusDays(1));

		// Act & Assert
		BusinessException exception = assertThrows(BusinessException.class, () -> service.create(validRequest));
		assertEquals("Data e hora não podem ser futuras", exception.getMessage());
	}

	@Test
	void createWithInvalidPlacaShouldThrowException() {
		// Arrange
		validRequest.setPlaca("INVALID");

		// Act & Assert
		BusinessException exception = assertThrows(BusinessException.class, () -> service.create(validRequest));
		assertEquals("Placa deve estar no formato AAA-1234 ou ABC1D23", exception.getMessage());
	}

	@Test
	void createWithLowerKilometersShouldThrowException() {
		// Arrange
		Abastecimento previousAbastecimento = new Abastecimento();
		previousAbastecimento.setQuilometragem(2000);

		when(repository.findTopByPlacaOrderByQuilometragemDesc(anyString()))
				.thenReturn(Optional.of(previousAbastecimento));

		// Act & Assert
		BusinessException exception = assertThrows(BusinessException.class, () -> service.create(validRequest));
		assertTrue(exception.getMessage().contains("Quilometragem deve ser maior que"));
	}

	@Test
	void listWithFilterSuccess() {
		// Arrange
		PageRequest pageRequest = PageRequest.of(0, 10);
		List<Abastecimento> abastecimentos = List.of(abastecimento);
		Page<Abastecimento> page = new PageImpl<>(abastecimentos, pageRequest, 1);

		when(repository.findByPlacaContainingIgnoreCase(anyString(), any(PageRequest.class))).thenReturn(page);

		// Act
		Page<AbastecimentoResponseDTO> response = service.list("ABC", pageRequest);

		// Assert
		assertNotNull(response);
		assertEquals(1, response.getTotalElements());
		assertEquals(abastecimento.getPlaca(), response.getContent().get(0).getPlaca());
	}

	@Test
	void deleteSuccess() {
		// Arrange
		when(repository.existsById(anyLong())).thenReturn(true);
		doNothing().when(repository).deleteById(anyLong());

		// Act & Assert
		assertDoesNotThrow(() -> service.delete(1L));
		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	void deleteNonExistentShouldThrowException() {
		// Arrange
		when(repository.existsById(anyLong())).thenReturn(false);

		// Act & Assert
		BusinessException exception = assertThrows(BusinessException.class, () -> service.delete(1L));
		assertEquals("Abastecimento não encontrado", exception.getMessage());
	}
}