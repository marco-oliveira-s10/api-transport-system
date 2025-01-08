package com.transport.system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.system.controller.AbastecimentoController;
import com.transport.system.dto.AbastecimentoRequestDTO;
import com.transport.system.dto.AbastecimentoResponseDTO;
import com.transport.system.service.AbastecimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AbastecimentoController.class)
public class AbastecimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AbastecimentoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private AbastecimentoRequestDTO validRequest;
    private AbastecimentoResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        
        validRequest = new AbastecimentoRequestDTO();
        validRequest.setDataHora(now);
        validRequest.setPlaca("ABC-1234");
        validRequest.setQuilometragem(1000);
        validRequest.setValorTotal(new BigDecimal("100.00"));

        responseDTO = new AbastecimentoResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setDataHora(now);
        responseDTO.setPlaca("ABC-1234");
        responseDTO.setQuilometragem(1000);
        responseDTO.setValorTotal(new BigDecimal("100.00"));
    }

    @Test
    void createSuccess() throws Exception {
        when(service.create(any(AbastecimentoRequestDTO.class)))
            .thenReturn(responseDTO);

        mockMvc.perform(post("/api/abastecimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.placa").value("ABC-1234"));
    }

    @Test
    void listSuccess() throws Exception {
        when(service.list(any(String.class), any(PageRequest.class)))
            .thenReturn(new PageImpl<>(List.of(responseDTO)));

        mockMvc.perform(get("/api/abastecimentos")
                .param("placa", "ABC")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].placa").value("ABC-1234"));
    }

    @Test
    void deleteSuccess() throws Exception {
        mockMvc.perform(delete("/api/abastecimentos/1"))
                .andExpect(status().isNoContent());
    }
}
