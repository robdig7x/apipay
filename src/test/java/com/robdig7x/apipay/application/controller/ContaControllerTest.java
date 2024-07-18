package com.robdig7x.apipay.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robdig7x.apipay.domain.model.dto.ContaDTO;
import com.robdig7x.apipay.domain.model.entity.Conta;
import com.robdig7x.apipay.domain.service.ContaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robdig7x.apipay.billpayer.domain.Conta;
import com.robdig7x.apipay.billpayer.dto.ContaDTO;
import com.robdig7x.apipay.billpayer.enums.Situacao;
import com.robdig7x.apipay.billpayer.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContaController.class)
public class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService contaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testCreateConta() throws Exception {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setValor(BigDecimal.valueOf(100));
        contaDTO.setDescricao("Teste");

        when(contaService.save(any(Conta.class))).thenReturn(new Conta());

        mockMvc.perform(post("/api/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testGetAllContas() throws Exception {
        when(contaService.findAll()).thenReturn(Collections.singletonList(new Conta()));

        mockMvc.perform(get("/api/contas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testGetContaById() throws Exception {
        Conta conta = new Conta();
        conta.setId(1L);
        when(contaService.findById(1L)).thenReturn(Optional.of(conta));

        mockMvc.perform(get("/api/contas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testUpdateConta() throws Exception {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setValor(BigDecimal.valueOf(100));
        contaDTO.setDescricao("Teste");

        Conta conta = new Conta();
        conta.setId(1L);
        when(contaService.update(any(Long.class), any(ContaDTO.class))).thenReturn(Optional.of(conta));

        mockMvc.perform(put("/api/contas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contaDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testDeleteConta() throws Exception {
        mockMvc.perform(delete("/api/contas/1"))
                .andExpect(status().isNoContent());
    }
}
*/
//@WebMvcTest(ContaController.class)
public class ContaControllerTest {

    @Test
    public void testee() {
        assertNotNull("Não implementado ainda.");
    }

    /*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService contaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateConta() throws Exception {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setValor(BigDecimal.valueOf(100));
        contaDTO.setDescricao("Teste");

        when(contaService.save(any(ContaDTO.class))).thenReturn(new Conta());

        mockMvc.perform(post("/api/contas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllContas() throws Exception {
        Page<Conta> conta = new PageImpl(List.of(new Conta()));
        when(contaService.findAllByDateAndDescription(any(), any(), any(), any(), any()))
                .thenReturn(conta);

        mockMvc.perform(get("/api/contas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetContaById() throws Exception {
        Conta conta = new Conta();
        conta.setId(1L);
        when(contaService.findById(1L)).thenReturn(conta);

        mockMvc.perform(get("/api/contas/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateConta() throws Exception {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setValor(BigDecimal.valueOf(100));
        contaDTO.setDescricao("Teste");

        Conta conta = new Conta();
        conta.setId(1L);
        when(contaService.update(any(Long.class), any(ContaDTO.class))).thenReturn(conta);

        mockMvc.perform(put("/api/contas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contaDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteConta() throws Exception {
        mockMvc.perform(delete("/api/contas/1"))
                .andExpect(status().isNoContent());
    }
*/
}